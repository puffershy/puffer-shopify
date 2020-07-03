package com.puffer.shopify.service;

import com.puffer.shopify.AbstractTest;
import org.assertj.core.util.Lists;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.*;

public class KeywordsServiceTest extends AbstractTest {

    @Resource
    private KeywordsService keywordsService;

    @Test
    public void testSaveCompite() throws IOException {
        String dir = "E:\\puffer\\doswarm\\keyword\\";

        List<String> fileList = Lists.newArrayList(dir.concat("1.xlsx"));

        keywordsService.saveCompite(fileList);

    }
}