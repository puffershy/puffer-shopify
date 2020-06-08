package com.puffer.shopify.common.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAliasType;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Data;

import java.util.List;

/**
 * @author puffer
 * @date 2020年06月08日 19:22:15
 * @since
 */
@Data
public class GoogleChannel {
    @XStreamAlias("title")
    private String title;
    @XStreamAlias("link")
    private String link;
    @XStreamAlias("description")
    private String description;

    // @XStreamAlias("item")
    @XStreamImplicit()
    private List<GoogleItem> item;
}
