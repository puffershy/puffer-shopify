package com.puffer.shopify.common.model;

import lombok.Data;

import java.util.List;

/**
 * @author puffer
 * @date 2020年01月06日 19:46:34
 * @since
 */
@Data
public class Page<T> {
    private String nextUrl;
    private String previousUrl;
    private List<T> list;

    public Page(List<T> list) {
        this.list = list;
    }

    public Page(String nextUrl, String previousUrl, List<T> list) {
        this.nextUrl = nextUrl;
        this.previousUrl = previousUrl;
        this.list = list;
    }
}
