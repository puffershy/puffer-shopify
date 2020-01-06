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

@Data
@Validated
@ConfigurationProperties(prefix = "puffer.shopify")
public class ShopifyProperties {

    @NotBlank
    private String domainUrl;

    @NotNull
    private PrivateAuth privateAuth;

    @Data
    public static class PrivateAuth {
        @NotBlank
        private String userName;

        @NotBlank
        private String password;
    }
}
