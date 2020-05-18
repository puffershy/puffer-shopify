package com.puffer.shopify.service;

import com.alibaba.fastjson.JSON;
import com.puffer.shopify.common.util.ShopifyHttpUitl;
import com.puffer.shopify.vo.shopify.ShopifyProduct;
import com.puffer.shopify.vo.shopify.ShopifyProductWrapper;
import okhttp3.*;
import org.springframework.http.MediaType;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * @author puffer
 * @date 2020年01月06日 13:55:04
 * @since
 */
public class ShopifyApiTest {
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    // @Resource
    // private ShopifyProperties shopifyProperties;

    @Test
    public void testPost() throws IOException {
        ShopifyProduct shopifyProduct = new ShopifyProduct();
        shopifyProduct.setTitle("auto create product"+System.currentTimeMillis());
        shopifyProduct.setBodyHtml("product description");
        shopifyProduct.setVendor("Burton");
        shopifyProduct.setProductType("auto test");

        ShopifyProductWrapper shopifyProductWrapper = new ShopifyProductWrapper();
        shopifyProductWrapper.setProduct(shopifyProduct);


        OkHttpClient client = buildBasicAuthClient("f5503e51af0b61af7f1c763712fbe787", "042d99c6b431c481f339bf32e60547b9");


        RequestBody requestBody = RequestBody.create(ShopifyHttpUitl.TYPE_JSON, JSON.toJSONString(shopifyProductWrapper));

        String url = "https://doswarm-dev.myshopify.com/admin/api/2020-01/products.json";

        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();

        System.out.println(response.body().string());

    }



    @Test
    public void testHttp() throws IOException {
        OkHttpClient client = buildBasicAuthClient("f5503e51af0b61af7f1c763712fbe787", "042d99c6b431c481f339bf32e60547b9");

        String url = "https://doswarm-dev.myshopify.com/admin/api/2020-01/products.json";

        Request request = new Request.Builder()
                .url(url)
                // .header("Authorization", token)
                // .header("Authorization", basic)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .get()
                .build();
        Response response = client.newCall(request).execute();

        System.out.println(response.body().string());
    }


    public OkHttpClient buildBasicAuthClient(final String name, final String password) {
        return new OkHttpClient.Builder().readTimeout(30000,TimeUnit.MILLISECONDS).authenticator(new Authenticator() {
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                String credential = Credentials.basic(name, password);
                return response.request().newBuilder().header("Authorization", credential).build();
            }
        }).build();
    }
}
