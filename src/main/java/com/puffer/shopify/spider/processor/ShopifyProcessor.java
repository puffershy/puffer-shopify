package com.puffer.shopify.spider.processor;

import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author buyi
 * @date 2020年06月09日 09:24:38
 * @since
 */
@Service
public class ShopifyProcessor implements PageProcessor {
    private Site site = Site.me().setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.129 Safari/537.36")
            .setTimeOut(60000).setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        String title = page.getHtml().xpath("//*[@id=\"shopify-section-product-template\"]/div[1]/div[2]/div[1]/div[2]/h1").toString();
        String description = page.getHtml().xpath("//*[@id=\"description\"]/allText()").toString();

        page.putField("title", title);
        page.putField("description", description);
        page.putField("url", page.getUrl().toString());
    }

    @Override
    public Site getSite() {
        return this.site;
    }
}
