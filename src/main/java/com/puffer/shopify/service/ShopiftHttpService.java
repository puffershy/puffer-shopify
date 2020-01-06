package com.puffer.shopify.service;

import com.alibaba.fastjson.JSONObject;
import com.puffer.core.log.Log;
import com.puffer.shopify.common.constants.ShopifyConstant;
import com.puffer.shopify.common.enums.ShopifyRelEnum;
import com.puffer.shopify.common.model.Page;
import com.puffer.shopify.common.util.ShopifyHttpUitl;
import com.puffer.shopify.config.ShopifyProperties;
import com.puffer.shopify.mapper.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
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
    private RestTemplate restTemplate;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ShopifyProperties shopifyProperties;

    public <T> Page<T> getPageInfo(String path, Class<T> clazz) {
        final String op = "ShopiftHttpService.getInfo";
        String url = shopifyProperties.getDomainUrl().concat(path);
        ShopifyProperties.PrivateAuth privateAuth = shopifyProperties.getPrivateAuth();
        Response response = ShopifyHttpUitl.getBasicAuth(privateAuth.getUserName(), privateAuth.getPassword(), url);

        if (HttpStatus.OK.value() != response.code()) {
            //如果不成功则返回空
            log.error(Log.newInstance(op, "请求http异常").toString());
            return null;
        }

        List<T> list = JSONObject.parseArray(response.body().toString(), clazz);

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

}
