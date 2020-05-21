package com.puffer.shopify.common.util;

import com.puffer.shopify.common.constants.PatternConstants;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import us.codecraft.webmagic.Page;

import java.math.BigDecimal;
import java.util.List;

/**
 * 亚马逊页面工具
 *
 * @author puffer
 * @date 2020年05月21日 19:25:49
 * @since 1.0.0
 */
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
    private static final List<String> PRICE_XPAHT = Lists.newArrayList("//*[@id=\"priceblock_ourprice\"]/text()", "//*[@id=\"priceblock_saleprice\"]/text()");

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

        price = price.replace("$", "");
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

    public static String queryRank(Page page) {
        return null;
    }

    public static String queryDescription(Page page) {
        return null;
    }
}
