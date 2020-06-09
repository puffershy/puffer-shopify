package com.puffer.shopify.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.puffer.shopify.common.enums.LanguageEnum;
import com.puffer.shopify.common.model.GoogleChannel;
import com.puffer.shopify.common.model.GoogleItem;
import com.puffer.shopify.common.model.GoogleLanguageXml;
import com.puffer.shopify.spider.pipeline.ShopifyPipeline;
import com.puffer.shopify.spider.processor.ShopifyProcessor;
import com.puffer.util.lang.ExcelUtil;
import com.puffer.util.xml.XMLUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * shopify多语言生成服务
 *
 * @author buyi
 * @date 2020年06月09日 09:56:39
 * @since 1.0.0
 */
@Service
public class ShopifyLanguageService {
    @Resource
    private ShopifyProcessor shopifyProcessor;

    @Resource
    private ShopifyPipeline shopifyPipeline;

    public void handle(String filePath, String toFilePath) throws IOException {
        List<Object[]> objects = ExcelUtil.readFile(filePath, 1);

        List<GoogleItem> items = Lists.newArrayList();

        List<String> urlList = Lists.newArrayList();

        // Map<String, List<String>> urlsMap = Maps.newConcurrentMap();
        Map<String, String> urlsMap = Maps.newConcurrentMap();

        Map<String, GoogleItem> urlItemMap = Maps.newConcurrentMap();

        for (Object[] object : objects) {
            GoogleItem googleItem = new GoogleItem();
            googleItem.setTitle(String.valueOf(object[0]));
            googleItem.setLink(String.valueOf(object[1]));
            googleItem.setDescription(String.valueOf(object[2]));
            googleItem.setGoogleProductCategory(String.valueOf(object[3]));
            googleItem.setItem_group_id(String.valueOf(object[4]));
            googleItem.setId(String.valueOf(object[5]));
            googleItem.setCondition(String.valueOf(object[6]));
            googleItem.setPrice(String.valueOf(object[7]));
            googleItem.setAvailability(String.valueOf(object[8]));
            googleItem.setImageLink(String.valueOf(object[9]));
            googleItem.setBrand(String.valueOf(object[10]));
            googleItem.setMpn(String.valueOf(object[11]));
            googleItem.setProductType(String.valueOf(object[12]));
            googleItem.setAgeGroup(String.valueOf(object[13]));
            googleItem.setGender(String.valueOf(object[14]));
            googleItem.setCustomLabel0(String.valueOf(object[15]));
            googleItem.setCustomLabel1(String.valueOf(object[16]));
            googleItem.setCustomLabel2(String.valueOf(object[17]));
            googleItem.setCustomLabel3(String.valueOf(object[18]));
            googleItem.setCustomLabel4(String.valueOf(object[19]));
            googleItem.setShippingWeight(String.valueOf(object[20]));
            googleItem.setPattern(String.valueOf(object[21]));
            googleItem.setColor(String.valueOf(object[22]));
            items.add(googleItem);

            String url = googleItem.getLink();
            // List<String> urls = Lists.newArrayList();
            for (LanguageEnum languageEnum : LanguageEnum.values()) {
                String txt = url.substring(0, 18).concat("/" + languageEnum.getCode()).concat(url.substring(18, url.length()));
                // urls.add(txt);

                urlsMap.put(txt, url);

                urlList.add(txt);
            }

            urlItemMap.put(url, googleItem);

        }

        shopifyPipeline.setUrlItemMap(urlItemMap);
        shopifyPipeline.setUrlsMap(urlsMap);

        Spider.create(new ShopifyProcessor())
                .addUrl()
                .addPipeline(shopifyPipeline)
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();

        items.addAll(shopifyPipeline.getItems());

        GoogleChannel googleChannel = new GoogleChannel();
        googleChannel.setTitle("GoJeek");
        googleChannel.setLink("https://gojeek.com");
        googleChannel.setDescription("");
        googleChannel.setItem(items);

        GoogleLanguageXml googleLanguageXml = new GoogleLanguageXml();
        googleLanguageXml.setChannel(googleChannel);

        String xml = XMLUtil.toXMLDom(googleLanguageXml);
        xml = "<?xml version=\"1.0\"?>\n" + xml;
        // System.out.println(xml);

        FileUtils.writeStringToFile(new File(toFilePath), xml);
    }

}
