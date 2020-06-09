package com.puffer.shopify.service;

import com.puffer.shopify.AbstractTest;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.io.IOException;

public class ShopifyLanguageServiceTest extends AbstractTest {

    @Resource
    private ShopifyLanguageService shopifyLanguageService;

    @Test
    public void testHandle() throws IOException {
        shopifyLanguageService.handle("E:\\puffer\\gojeek\\GOJEEK - 英文xml文件模板.xlsx", "E:\\puffer\\gojeek\\gojeek.xml");
    }
}