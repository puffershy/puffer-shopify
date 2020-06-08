package com.puffer.shopify.common.model;

import com.google.common.collect.Lists;
import com.puffer.util.lang.ExcelUtil;
import com.puffer.util.xml.XMLUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Data;

import java.io.IOException;
import java.util.List;

/**
 * 谷歌多语言实体
 *
 * @author puffer
 * @date 2020年06月08日 19:00:40
 * @since 1.0.0
 */
@Data
@XStreamAlias("rss")
public class GoogleLanguageXml {

    @XStreamAsAttribute
    @XStreamAlias("xmlns:g")
    private String xmlnsg = "http://base.google.com/ns/1.0";

    @XStreamAsAttribute
    private String version = "2.0";

    @XStreamAlias("channel")
    private GoogleChannel channel;

    public static void main(String[] args) throws IOException {
        List<Object[]> objects = ExcelUtil.readFile("E:\\puffer\\gojeek\\GOJEEK - 英文xml文件模板.xlsx", 1);

        List<GoogleItem> items = Lists.newArrayList();

        int i = 1;
        for (Object[] object : objects) {
            try {
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
            } catch (Exception e) {
                // e.printStackTrace();
                System.err.println("异常：" + i + e.getMessage());

            }

            i++;
        }

        GoogleChannel googleChannel = new GoogleChannel();
        googleChannel.setTitle("GoJeek");
        googleChannel.setLink("https://gojeek.com");
        googleChannel.setDescription("");
        googleChannel.setItem(items);

        GoogleLanguageXml googleLanguageXml = new GoogleLanguageXml();
        googleLanguageXml.setChannel(googleChannel);

        String xml = XMLUtil.toXML(googleLanguageXml);
        System.out.println(xml);

    }
}
