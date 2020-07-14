package com.puffer.shopify.service;

import com.puffer.shopify.AbstractTest;
import org.assertj.core.util.Lists;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

public class KeywordsServiceTest extends AbstractTest {

    @Resource
    private KeywordsService keywordsService;

    @Test
    public void testSaveCompite() throws IOException {
        String dir = "E:\\buyi\\doswarm\\keyword\\";

        List<String> fileList = Lists.newArrayList(dir.concat("1.xlsx"));

        keywordsService.saveCompite(fileList, "");
    }

    @Test
    public void updateId(){
        // keywordsService.updateId();
    }
}