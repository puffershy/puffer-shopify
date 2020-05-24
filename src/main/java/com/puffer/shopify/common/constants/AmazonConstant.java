package com.puffer.shopify.common.constants;


import org.assertj.core.util.Lists;

import java.util.List;

/**
 * amazon 常量
 *
 * @author puffer
 * @date 2020年05月23日 15:20:29
 * @since 1.0.0
 */

public class AmazonConstant {

    public static final String FREE_SHIPPING = "FREE Shipping";

    /**
     * 图片base64报文头
     */
    public static final String BASE64_PRIFIX = "data:image/jpeg;base64,";

    public static final int SPIDER_THREAD_NUM = 5;

    /**
     * 内容关键字需要剔除的
     */
    public static final List<String> DESCRIPTION_REMOVE_TEXT = Lists.newArrayList();

    static {
        DESCRIPTION_REMOVE_TEXT.add("Money Back");
        DESCRIPTION_REMOVE_TEXT.add("in the USA");
    }

}