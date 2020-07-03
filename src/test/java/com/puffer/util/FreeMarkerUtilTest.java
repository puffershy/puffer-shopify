package com.puffer.util;

import com.puffer.shopify.vo.DescriptionFormatVO;
import com.puffer.shopify.vo.DescriptionHtmlVO;
import com.puffer.util.lang.FreeMarkerUtil;
import com.puffer.util.reflect.BeanUtil;
import org.testng.annotations.Test;
import org.testng.collections.Maps;

import java.util.Map;

/**
 * @author puffer
 * @date 2020年07月03日 10:01:14
 * @since
 */
public class FreeMarkerUtilTest {

    @Test
    public void testDescriptionHtml() throws IllegalAccessException {
        String templeteCode = "001";
        String templeteContent = "<p>${description}</p>\n"
                + "<p><strong>DETAIL</strong><br></p>\n"
                + "<ul>\n"
                + "<li>Capacity: ${capacity} oz</li>\n"
                + "<li>Material: ceramic</li>\n"
                + "<li>Dishwasher and microwave safe</li>\n"
                + "<li>${include!\" 1 * Mug\"}</li>\n"
                + "</ul>";

        DescriptionHtmlVO descriptionHtmlVO = new DescriptionHtmlVO();
        descriptionHtmlVO.setDescription("描述测试");
        descriptionHtmlVO.setCapacity("11");
        // descriptionHtmlVO.setInclude("");
        descriptionHtmlVO.setSafe("123");
        Map<String, Object> stringObjectMap = BeanUtil.toMap(descriptionHtmlVO);

        String process = FreeMarkerUtil.process(templeteCode, templeteContent, stringObjectMap);
        System.out.println(process);
    }


    @Test
    public void testDescription() throws IllegalAccessException {
        String templeteCode = "002";
        String templeteContent = "${title} ${material}<#if quote?? && quote != \"\"> The mug reads: \"${quote}\".</#if> ${sceneFor}";

        DescriptionFormatVO descriptionFormatVO = new DescriptionFormatVO();
        descriptionFormatVO.setTitle("The Teacher Coffee");
        descriptionFormatVO.setMaterial("is made up of 100% pure white high gloss ceramic.");
        descriptionFormatVO.setQuote("");
        descriptionFormatVO.setSceneFor("This novelty mug is an unforgettable addition to any coffee drinker's cupboard; the cute design is unique and memorable, the perfect way to celebrate coffee time.");

        Map<String, Object> stringObjectMap = BeanUtil.toMap(descriptionFormatVO);

        String process = FreeMarkerUtil.process(templeteCode, templeteContent, stringObjectMap);
        System.out.println(process);
    }
}
