package com.puffer.shopify.service;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
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
    public void testHttp() throws IOException {

        // String token = Base64Utils.encodeToString((":e77c41384e1620a8d2b630ffd6260033").getBytes(UTF_8));

        String basic = Credentials.basic("", "e77c41384e1620a8d2b630ffd6260033");

        // OkHttpClient client = new OkHttpClient.Builder()
        //         .connectTimeout(10000L, TimeUnit.MILLISECONDS)
        //         .readTimeout(10000L, TimeUnit.MILLISECONDS)
        //         .build();

        OkHttpClient client = buildBasicAuthClient("", "e77c41384e1620a8d2b630ffd6260033");

        // String url = shopifyProperties.sgetDomainUrl()+"/admin/api/2020-01/products.json";
        String url = "https://xx.myshopify.com/admin/api/2020-01/products.json";

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
        return new OkHttpClient.Builder().authenticator(new Authenticator() {
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                String credential = Credentials.basic(name, password);
                return response.request().newBuilder().header("Authorization", credential).build();
            }
        }).build();
    }
}
