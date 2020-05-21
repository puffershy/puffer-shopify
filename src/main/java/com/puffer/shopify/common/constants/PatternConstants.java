package com.puffer.shopify.common.constants;

/**
 * 正则表达式常量
 *
 * @author puffer
 * @date 2019年10月20日 15:29:17
 * @since 1.0.0
 */
public class PatternConstants {

    /**
     * 获取数字表达式<br>
     * SF123.134JKJ--123.134
     * SDF123.SDF--123
     * SDF123SDF--123
     * 123,113.123--123,113.123
     * 123,13.123--123
     *
     * @author puffer
     * @date 2019年10月20日 15:38:48
     * @since 1.0.0
     */
    public static final String NUMBER = "(\\d+)(,\\d{3})*(\\.\\d+)?";

    public static final String SPU_PREFFIX = "/dp/";
    public static final String SPU = "/dp/(\\w+)";

    /**
     * 亚马逊销售排行版url正则匹配
     */
    public static final String AMAZON_BEST_SELLER_URL = ".*Best-Sellers.*";
}
