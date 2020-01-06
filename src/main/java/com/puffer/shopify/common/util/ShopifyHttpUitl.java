package com.puffer.shopify.common.util;

import com.puffer.core.exception.CommonExceptionCode;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Shopify http工具类
 *
 * @author puffer
 * @date 2020年01月06日 19:24:36
 * @since 1.0.0
 */
public class ShopifyHttpUitl {
    private static OkHttpClient httpClient;

    static {
        OkHttpClient.Builder builder = (new OkHttpClient.Builder()).connectTimeout(30000L, TimeUnit.MILLISECONDS).readTimeout(30000L, TimeUnit.MILLISECONDS);
        httpClient = builder.build();
    }

    public static Response getBasicAuth(String username, String password, String url) {
        String basic = Credentials.basic(username, password);
        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", basic)
                .get().build();

        try {
            return httpClient.newCall(request).execute();
        } catch (IOException e) {
            throw CommonExceptionCode.BAD_PARAMETER.exception();
        }
    }

}
