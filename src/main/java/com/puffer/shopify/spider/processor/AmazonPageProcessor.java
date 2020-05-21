package com.puffer.shopify.spider.processor;

import com.puffer.shopify.common.constants.PatternConstants;
import com.puffer.shopify.common.util.AmazonPageUtil;
import com.puffer.shopify.common.util.PatterUtil;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;

/**
 * 亚马逊爬虫
 *
 * @author puffer
 * @date 2019年10月13日 19:32:56
 * @since 1.0.0
 */
public class AmazonPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    private static final String LIST_URL_PATTER = ".*Best-Sellers.*";

    @Override
    public void process(Page page) {
        if (page.getUrl().regex(LIST_URL_PATTER).match()) {
            //如果当前页面是列表页面

            //添加所有产品的连接
            // page.addTargetRequests(page.getHtml().xpath("//*[@id=\"zg-right-col\"]").links().all());
            page.addTargetRequests(AmazonPageUtil.queryProductUrlList(page));

            //获取所有分页的连接
            // page.addTargetRequests(page.getHtml().xpath("//*[@id=\"zg-center-div\"]/div[2]/div").links().all());
            page.addTargetRequests(AmazonPageUtil.queryPageUrlList(page));
            page.setSkip(true);
        } else {

            //构建产品信息
            buildProduct(page);
        }
    }

    private void buildProduct(Page page) {
        String title = AmazonPageUtil.queryTitle(page);
        String url = page.getUrl().toString();
        String spu = AmazonPageUtil.querySpu(page);
        BigDecimal price = AmazonPageUtil.queryPrice(page);
        String description = AmazonPageUtil.queryDescription(page);

        if (StringUtils.isBlank(title)) {
            page.setSkip(true);
            return;
        }

        // page.putField("title", title);
    }

    // private String getPrice(Page page) {
    //     List<String> xpahtList = Lists.newArrayList();
    //     xpahtList.add("//*[@id=\"priceblock_ourprice\"]/text()");
    //     xpahtList.add("//*[@id=\"priceblock_saleprice\"]/text()");
    //     String price = "";
    //     for (String xpath : xpahtList) {
    //         price = page.getHtml().xpath(xpath).toString();
    //         if (StringUtils.isNotBlank(price)) {
    //             return price;
    //         }
    //     }
    //     return "";
    // }

    private String getRank(Page page) {
        List<String> xpahtList = Lists.newArrayList();

        //*[@id="productDetails_detailBullets_sections1"]/tbody/tr[8]/th
        //*[@id="productDetails_detailBullets_sections1"]/tbody/tr[8]/td/span/span[1]
        xpahtList.add("//*[@id=\"productDetails_detailBullets_sections1\"]/tbody/tr[7]/td/span/span[2]/text()");
        xpahtList.add("*[@id=\"productDetails_detailBullets_sections1\"]/tbody/tr[6]/td/span/span[2]/text()");
        xpahtList.add("//*[@id=\"productDetails_detailBullets_sections1\"]/tbody/tr[8]/td/span/span[3]/text()");
        String rank = "";
        for (String xpath : xpahtList) {
            rank = page.getHtml().xpath(xpath).toString();
            if (StringUtils.isNotBlank(rank)) {
                Matcher matcher = PatterUtil.instance(PatternConstants.NUMBER).matcher(rank);
                matcher.find();
                return matcher.group();
            }
        }
        return "";
    }

    @Override
    public Site getSite() {
        return site;
    }

    // public static void main(String[] args) {
    //
    //     // Spider.create(new AmazonPageProcessor())
    //     //         .addUrl("https://www.amazon.com/gp/new-releases/home-garden/13749881/ref=zg_bs_tab_t_bsnr")
    //     //         .addPipeline(new GitHubPipeline2())
    //     //         //开启5个线程抓取
    //     //         .thread(5)
    //     //         //启动爬虫
    //     //         .run();
    // }
}
