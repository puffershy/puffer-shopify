package com.puffer.shopify.common.util;

import com.google.common.collect.Maps;
import com.puffer.core.exception.CommonExceptionCode;
import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Shopify http工具类
 *
 * @author puffer
 * @date 2020年01月06日 19:24:36
 * @since 1.0.0
 */
public class ShopifyHttpUitl {

    public static final okhttp3.MediaType TYPE_JSON = okhttp3.MediaType.parse("application/json; charset=utf-8");

    private static OkHttpClient httpClient;

    private static final Map<String, OkHttpClient> authClientMap = Maps.newConcurrentMap();

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

    /**
     * 实例化一个okhttpClient客户端
     *
     * @param name
     * @param password
     * @return okhttp3.OkHttpClient
     * @author puffer
     * @date 2020年05月15日 09:32:22
     * @since 1.0.0
     */
    public static OkHttpClient instanceBasicAuthClient(final String name, final String password) {
        String key = buildKey(name, password);
        if (authClientMap.containsKey(key)) {
            return authClientMap.get(key);
        }

        return syncInstanceBasicAuthClient(name, password);
    }

    /**
     * 同步锁 初始化okhttp
     *
     * @param name
     * @param password
     * @return okhttp3.OkHttpClient
     * @author puffer
     * @date 2020年05月15日 09:39:16
     * @since 1.0.0
     */
    private static synchronized OkHttpClient syncInstanceBasicAuthClient(String name, String password) {

        String key = buildKey(name, password);
        if (authClientMap.containsKey(key)) {
            return authClientMap.get(key);
        }

        OkHttpClient okHttpClient = buildBasicAuthClient(name, password);

        authClientMap.put(key, okHttpClient);

        return okHttpClient;
    }

    public static OkHttpClient buildBasicAuthClient(final String name, final String password) {
        return new OkHttpClient.Builder().readTimeout(100000, TimeUnit.MILLISECONDS).authenticator(new Authenticator() {
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                String credential = Credentials.basic(name, password);
                return response.request().newBuilder().header("Authorization", credential).build();
            }
        }).build();
    }

    private static String buildKey(String name, String password) {
        return name.concat("-").concat(password);
    }

}
