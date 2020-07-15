package com.puffer.shopify.config;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * shopify配置
 *
 * @author puffer
 * @date 2020年01月06日 12:24:01
 * @since 1.0.0
 */

@Validated
@ConfigurationProperties(prefix = "puffer.shopify")
public class ShopifyProperties {

    /**
     * 是否代理，true 代理，flase-不是设置代理
     */
    private boolean proxy;

    @NotBlank
    private String domainUrl;

    @NotNull
    private String adminApi;

    @NotNull
    private PrivateAuth privateAuth;

    public boolean isProxy() {
        return proxy;
    }

    public void setProxy(boolean proxy) {
        this.proxy = proxy;
    }

    public String getDomainUrl() {
        return domainUrl;
    }

    public void setDomainUrl(String domainUrl) {
        this.domainUrl = domainUrl;
    }

    public String getAdminApi() {
//        return adminApi;
        return getDomainUrl().concat(adminApi);
    }

    public void setAdminApi(String adminApi) {
        this.adminApi = adminApi;
    }

    public PrivateAuth getPrivateAuth() {
        return privateAuth;
    }

    public void setPrivateAuth(PrivateAuth privateAuth) {
        this.privateAuth = privateAuth;
    }

    @Data
    public static class PrivateAuth {
        @NotBlank
        private String userName;

        @NotBlank
        private String password;
    }



}
