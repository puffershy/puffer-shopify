package com.puffer.shopify.service.shopify;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.puffer.core.exception.CommonExceptionCode;
import com.puffer.core.log.Log;
import com.puffer.shopify.common.constants.ShopifyConstant;
import com.puffer.shopify.common.enums.ShopifyRelEnum;
import com.puffer.shopify.common.model.Page;
import com.puffer.shopify.common.util.ShopifyHttpUitl;
import com.puffer.shopify.config.ShopifyProperties;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * http服务
 *
 * @author puffer
 * @date 2020年01月06日 13:41:41
 * @since 1.0.0
 */
@Slf4j
@Service
public class ShopiftHttpService {

    private static Pattern pattern = Pattern.compile("<(.*?)>");
    private static Pattern relPattern = Pattern.compile("\\\"([^\\\"]*)\\\"");

    @Resource
    private ShopifyProperties shopifyProperties;

    private OkHttpClient instanceClient() {
        ShopifyProperties.PrivateAuth privateAuth = shopifyProperties.getPrivateAuth();
        if (shopifyProperties.isProxy()) {
            //如果设置了代理
            return ShopifyHttpUitl.instanceBasicAuthClientProxy(privateAuth.getUserName(), privateAuth.getPassword());
        }
        return ShopifyHttpUitl.instanceBasicAuthClient(privateAuth.getUserName(), privateAuth.getPassword());
    }

    /**
     * post请求
     *
     * @param path
     * @param params
     * @param clazz
     * @return T
     * @author puffer
     * @date 2020年05月15日 10:21:01
     * @since 1.0.0
     */
    public <T> T post(String path, Object params, Class<T> clazz) {
        ShopifyProperties.PrivateAuth privateAuth = shopifyProperties.getPrivateAuth();

        OkHttpClient client = instanceClient();

        String url = shopifyProperties.getAdminApi().concat(path);
        RequestBody requestBody = RequestBody.create(ShopifyHttpUitl.TYPE_JSON, JSON.toJSONString(params));

        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            log.info("http请求响应参数：" + string);
            return JSONObject.parseObject(string, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("请求shopify异常");
        }
    }

    public <T> T get(String path, Class<T> clazz) {
        final String op = "ShopiftHttpService.get";
        OkHttpClient client = instanceClient();
        String url = shopifyProperties.getAdminApi().concat(path);

        Request request = new Request.Builder().get()
                .url(url)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            log.info("http请求响应参数：" + string);
            return JSONObject.parseObject(string, clazz);
        } catch (Exception e) {
            log.error(Log.newInstance(op, "shopify http exception").toString(), e);
            throw CommonExceptionCode.SYS_ERROR.exception();
        }
    }

    public <T> Page<T> getPageInfo(String path, Class<T> clazz) throws IOException {
        final String op = "ShopiftHttpService.getInfo";
        String url = shopifyProperties.getDomainUrl().concat(path);
        ShopifyProperties.PrivateAuth privateAuth = shopifyProperties.getPrivateAuth();
        Response response = ShopifyHttpUitl.getBasicAuth(privateAuth.getUserName(), privateAuth.getPassword(), url);

        if (HttpStatus.OK.value() != response.code()) {
            //如果不成功则返回空
            log.error(Log.newInstance(op, "请求http异常").toString());
            return null;
        }

        List<T> list = JSONObject.parseArray(response.body().string(), clazz);

        Page<T> page = new Page<>(list);

        String link = response.header(ShopifyConstant.PAGE_REPONSE_HEADER_LINK);
        //解析link
        for (String s : link.split(",")) {
            Matcher relMatcher = relPattern.matcher(s);

            String rel = "";
            if (relMatcher.find()) {
                rel = relMatcher.group(1);
            }

            Matcher matcher = pattern.matcher(s);
            String pageUrl = "";
            if (matcher.find()) {
                pageUrl = matcher.group(1);
            }

            if (ShopifyRelEnum.NEXT.isValue(rel)) {
                page.setNextUrl(pageUrl);
            } else {
                page.setPreviousUrl(pageUrl);
            }

        }

        return page;
    }

    public <T> T put(String path, Object object, Class<T> clazz) {
        final String op = "put";
        OkHttpClient client = instanceClient();
        String url = shopifyProperties.getAdminApi().concat(path);

        RequestBody requestBody = RequestBody.create(ShopifyHttpUitl.TYPE_JSON, JSON.toJSONString(object));

        Request request = new Request.Builder().put(requestBody)
                .url(url)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            log.info("http请求响应参数：" + string);
            return JSONObject.parseObject(string, clazz);
        } catch (Exception e) {
            log.error(Log.newInstance(op, "shopify http exception").toString(), e);
            throw CommonExceptionCode.SYS_ERROR.exception();
        }
    }
}
