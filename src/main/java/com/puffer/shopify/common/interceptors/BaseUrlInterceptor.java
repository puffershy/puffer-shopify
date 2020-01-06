package com.puffer.shopify.common.interceptors;

import com.puffer.shopify.config.ShopifyProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author puffer
 * @date 2020年01月06日 12:18:42
 * @since
 */
@Component
public class BaseUrlInterceptor  implements ClientHttpRequestInterceptor {

    @Resource
    private ShopifyProperties shopifyProperties;

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {

        HttpHeaders headers = httpRequest.getHeaders();

        //
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        return null;
    }
}
