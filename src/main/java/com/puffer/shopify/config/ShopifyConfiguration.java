package com.puffer.shopify.config;

import com.puffer.shopify.common.interceptors.BaseUrlInterceptor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @author puffer
 * @date 2020年01月06日 12:28:52
 * @since
 */

@Configuration
@EnableConfigurationProperties({ ShopifyProperties.class })
public class ShopifyConfiguration {

    private final ShopifyProperties shopifyProperties;

    public ShopifyConfiguration(ShopifyProperties shopifyProperties) {
        this.shopifyProperties = shopifyProperties;
    }

    @Bean
    public RestTemplate privateAuthRestTemplate(BaseUrlInterceptor baseUrlInterceptor) {

        // Buffer the request/response so it can be intercepted and read.
        ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());

        RestTemplate restTemplate = new RestTemplate(factory);
        // restTemplate.setMessageConverters(Collections.singletonList(messageConverter));
        ShopifyProperties.PrivateAuth privateAuth = shopifyProperties.getPrivateAuth();
        restTemplate.setInterceptors(Arrays.asList(
                new BasicAuthorizationInterceptor(privateAuth.getUserName(), privateAuth.getPassword()),
                baseUrlInterceptor));
        return restTemplate;
    }

}
