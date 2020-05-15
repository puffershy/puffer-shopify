package com.puffer.shopify.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * shopify产品实体
 *
 * @author puffer
 * @date 2020年01月06日 17:35:48
 * @since 1.0.0
 */
@Data
public class ShopifyProduct {

    /**
     * 产品唯一标识
     */
    private String id;

    /**
     * 产品名称
     */
    private String title;

    /**
     * 产品说明
     */
    @JSONField(name="body_html")
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
     * 创建时间
     */
    @JSONField(name = "create_at")
    private String createdAt;

    /**
     *
     * "handle": "ipod-nano"
     * 产品的独特的人性化字符串。自动从产品的生成title。Liquid模板语言使用它来引用对象。
     */
    private String handle;

    /**
     * 更新时间
     */
    @JSONField(name = "updated_at")
    private String updateAt;

    /**
     * 发布时间
     */
    @JSONField(name = "published_at")
    private String pulbishedAt;

    /**
     * 产品页面使用的Liquid模板的后缀。如果指定了此属性，那么产品页面将使用一个名为“ product.suffix.liquid”的模板，其中“ suffix”是此属性的值。
     * 如果此属性是""或null，那么产品页面将使用默认模板“ product.liquid”。（默认值：null
     */
    @JSONField(name = "template_suffix")
    private String templateSuffix;

    /**
     * 产品是否已发布到销售点渠道。有效值：
     * <p>
     * web：产品已发布到“在线商店”渠道，但未发布到“销售点”渠道。
     * global：产品同时发布到在线商店渠道和销售点渠道。
     */
    @JSONField(name = "published_scope")
    private String publishedScope;

    /**
     * 一串用逗号分隔的标记，用于过滤和搜索。一个产品最多可以包含250个标签。每个标签最多可包含255个字符
     */
    private String tags;

    /**
     *
     */
    @JSONField(name = "admin_graphql_api_id")
    private String adminGraphqlApiId;

    private List<ShopifyVariant> variants;

    /**
     * 自定义产品属性名称一样Size，Color和Material。您最多可以添加3个选项，每个选项最多255个字符。
     */
    private List<ShopifyOption> options;

    /**
     * 产品图像对象的 列表，每个代表与产品关联的图像。
     */
    private List<ShopifyImage> images;

    private ShopifyImage image;
}
