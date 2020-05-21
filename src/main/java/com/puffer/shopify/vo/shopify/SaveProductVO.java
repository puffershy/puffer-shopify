package com.puffer.shopify.vo.shopify;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * 保存产品请求参数
 *
 * @author puffer
 * @date 2020年05月18日 19:27:22
 * @since 1.0.0
 */
@Data
public class SaveProductVO {
    private SaveProductDetail product;

    @Data
    public static class SaveProductDetail {
        /**
         * 产品名称
         */
        private String title;

        /**
         * 产品说明
         */
        @JSONField(name = "body_html")
        private String bodyHtml;

        /**
         * 产品供应商的名称。
         */
        private String vendor;

        /**
         * 用于过滤和搜索产品的产品的分类
         */
        @JSONField(name = "product_type")
        private String productType;

        /**
         * 一串用逗号分隔的标记，用于过滤和搜索。一个产品最多可以包含250个标签。每个标签最多可包含255个字符
         */
        private String tags;

        /**
         * 发布状态
         */
        private boolean published = true;

        private List<Variant> variants;

        /**
         * 自定义产品属性名称一样Size，Color和Material。您最多可以添加3个选项，每个选项最多255个字符。
         */
        private List<Option> options;

        /**
         * 产品图像对象的 列表，每个代表与产品关联的图像。
         */
        private List<Image> images;

        private List<Metafield> metafields;
    }

    @Data
    public static class Variant {
        private String option1;
        private BigDecimal price;
        private String sku;

    }

    @Data
    public static class Option {
        private String name;
        private List<String> values = new LinkedList<>();
    }

    @Data
    public static class Image {
        /**
         * 图片网路地址，如果传src 就可以不用传attachment。，shopify会自动下载
         */
        private String src;
        /**
         * 图片 base64
         */
        private String attachment;
    }

    @Data
    public static class Metafield {
        private String key;
        private String value;
        @JSONField(name = "value_type")
        private String valueType;
        private String namespace;
    }
}
