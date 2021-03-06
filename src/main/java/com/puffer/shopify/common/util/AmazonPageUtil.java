package com.puffer.shopify.common.util;

import com.puffer.core.log.Log;
import com.puffer.shopify.common.constants.AmazonConstant;
import com.puffer.shopify.common.constants.PatternConstants;
import com.puffer.shopify.entity.ProductImageDO;
import com.puffer.shopify.entity.ProductRankDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import us.codecraft.webmagic.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 亚马逊页面工具
 *
 * @author puffer
 * @date 2020年05月21日 19:25:49
 * @since 1.0.0
 */
@Slf4j
public class AmazonPageUtil {

    /**
     * 产品urlXPATH
     */
    private static final String PRODUCT_URL_XPATH = "//*[@id=\"zg-right-col\"]";

    /**
     * 分页连接XPATH
     */
    private static final String PAGE_URL_XPATH = "//*[@id=\"zg-center-div\"]/div[2]/div";

    /**
     * 产品标题XPATH
     */
    private static final String TITLE_XPATH = "//*[@id=\"productTitle\"]/text()";

    /**
     * 产品价格
     */
    //    private static final List<String> PRICE_XPAHT = Lists.newArrayList("//*[@id=\"priceblock_ourprice\"]/text()", "//*[@id=\"priceblock_saleprice\"]/text()");
    private static final List<String> PRICE_XPAHT = Lists.newArrayList("//*[@id=\"price_inside_buybox\"]/text()","//*[@id=\"priceblock_ourprice\"]/text()");

    /**
     * 是否包邮
     */
    private static final String FREE_SHIPPING_XPAHT = "//*[@id=\"price-shipping-message\"]/b/text() | //*[@id=\"shippingMessageInsideBuyBox_feature_div\"]/div/div/div/span/b/text()";

    /**
     * 变体
     */
    private static final String VARIANT_XPATH = "//*[@id=\"variation_color_name\"]";

    /**
     * 排行版
     */
    private static final String BEST_SELLER_RANK_XPATH = "//*[@id=\"productDetails_detailBullets_sections1\"]/tbody/tr/td/span/span/allText()";

    /**
     * 描述
     */
    private static final String DESCRIPTION_XPATH = "//*[@id=\"feature-bullets\"]/ul/li/allText()";

    /**
     * 图片 //*[@id="ivLargeImage"]/img
     */
    //    private static final String IMAGE_XPATH = "//*[@id=\"landingImage\"]/@src";
    private static final String IMAGE_XPATH = "//*[@id=\"imgTagWrapperId\"]/img/@data-old-hires";

    public static boolean isListPage(Page page) {
        return page.getUrl().regex(PatternConstants.AMAZON_BEST_SELLER_URL).match();
    }

    /**
     * 获取所有产品的连接
     *
     * @param page
     * @return java.util.List<java.lang.String>
     * @author puffer
     * @date 2020年05月21日 19:50:31
     * @since 1.0.0
     */
    public static List<String> queryProductUrlList(Page page) {
        return page.getHtml().xpath(PRODUCT_URL_XPATH).links().all();
    }

    /**
     * 获取分页的所有链接
     *
     * @param page
     * @return java.util.List<java.lang.String>
     * @author puffer
     * @date 2020年05月21日 19:51:36
     * @since 1.0.0
     */
    public static List<String> queryPageUrlList(Page page) {
        return page.getHtml().xpath(PAGE_URL_XPATH).links().all();
    }

    /**
     * 查询产品标题
     *
     * @param page
     * @return java.lang.String
     * @author puffer
     * @date 2020年05月21日 19:33:40
     * @since 1.0.0
     */
    public static String queryTitle(Page page) {
        return page.getHtml().xpath(TITLE_XPATH).toString();
    }

    /**
     * 查询价格
     *
     * @param page
     * @return java.math.BigDecimal
     * @author puffer
     * @date 2020年05月21日 19:37:23
     * @since 1.0.0
     */
    public static BigDecimal queryPrice(Page page) {
        String price = "";
        for (String xpath : PRICE_XPAHT) {

            String str = page.getHtml().xpath(xpath).toString();
            if (StringUtils.isNotBlank(str)) {
                price = str;
                break;
            }
        }

        if (StringUtils.isBlank(price)) {
            log.info(Log.newInstance("", "价格为空，跳过").kv("url", page.getUrl().toString()).toString());
            return null;
        }

        price = price.replace("$", "").trim();
        return new BigDecimal(price);
    }

    /**
     * 查询产品SPU
     *
     * @param page
     * @return java.lang.String
     * @author puffer
     * @date 2020年05月21日 19:38:16
     * @since 1.0.0
     */
    public static String querySpu(Page page) {
        return page.getUrl().regex(PatternConstants.SPU).toString();
    }

    /**
     * 获取排行榜
     *
     * @param page
     * @param spu
     * @return java.util.List<com.puffer.shopify.entity.ProductRankDO>
     * @author puffer
     * @date 2020年05月22日 00:25:59
     * @since 1.0.0
     */

    public static List<ProductRankDO> queryRank(Page page, String spu) {

        List<ProductRankDO> list = Lists.newArrayList();

        List<String> all = page.getHtml().xpath(BEST_SELLER_RANK_XPATH).all();

        int endIndex;
        String tmp = "";
        int splitIndex;

        for (String s : all) {
            tmp = s.replace("#", "");

            endIndex = tmp.length();
            int i = s.indexOf("(");
            if (i > 0) {
                endIndex = i;
            }

            splitIndex = tmp.indexOf("in");

            ProductRankDO productRankDO = new ProductRankDO();
            productRankDO.setSpu(spu);
            productRankDO.setRank(Integer.valueOf(tmp.substring(0, splitIndex).trim().replace(",", "")));
            productRankDO.setRankType(s.substring(splitIndex + 3, endIndex).trim());

            list.add(productRankDO);
        }

        return list;
    }

    /**
     * 获取描述
     *
     * @param page
     * @return java.lang.String
     * @author puffer
     * @date 2020年05月22日 23:05:59
     * @since 1.0.0
     */

    public static String queryDescription(Page page) {

        Pattern emoji = PatterUtil.instance(PatternConstants.EMOJI);
        StringBuilder builder = new StringBuilder("<ul>");
        List<String> all = page.getHtml().xpath(DESCRIPTION_XPATH).all();
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                continue;
            }

            String content = all.get(i);

            Matcher emojiMatcher = emoji.matcher(content);

            if (emojiMatcher.find()) {
                content = emojiMatcher.replaceAll("");
            }


            for (String s : AmazonConstant.DESCRIPTION_REMOVE_TEXT) {
                if (content.contains(s)) {
                    content = null;
                    break;
                }
            }

            if (StringUtils.isBlank(content)) {
                continue;
            }
            builder.append("<li>").append(content).append("</li>");
        }

        builder.append("</ul>");

        return builder.toString();
    }

    public static void main(String[] args) {

    }

    /**
     * 图片信息
     *
     * @param page
     * @param spu
     * @return java.util.List<com.puffer.shopify.entity.ProductImageDO>
     * @author puffer
     * @date 2020年05月23日 15:06:10
     * @since 1.0.0
     */

    public static List<ProductImageDO> queryImage(Page page, String spu) {
        List<ProductImageDO> list = Lists.newArrayList();

        String content = page.getHtml().xpath(IMAGE_XPATH).toString();

        if (StringUtils.isBlank(content)) {
            content = page.getHtml().xpath("//*[@id=\"imgTagWrapperId\"]/img/@src").toString();
        }

        ProductImageDO productImageDO = new ProductImageDO();
        productImageDO.setSpu(spu);

        if (content.startsWith("http")) {
            productImageDO.setImageUrl(content);
        } else {
            productImageDO.setAttachment(content.trim().replace(AmazonConstant.BASE64_PRIFIX, ""));
        }

        list.add(productImageDO);

        return list;
    }

    /**
     * 查询是否免邮费
     *
     * @param page
     * @return java.lang.String
     * @author puffer
     * @date 2020年05月23日 15:23:52
     * @since 1.0.0
     */

    public static boolean queryFreeShipping(Page page) {
        String s = page.getHtml().xpath(FREE_SHIPPING_XPAHT).toString();
        if (StringUtils.isBlank(s)) {
            return false;
        }
        return s.contains(AmazonConstant.FREE_SHIPPING);
    }
}
