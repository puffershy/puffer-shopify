package com.puffer.shopify.spider.pipeline;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.puffer.shopify.common.model.GoogleItem;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author buyi
 * @date 2020年06月09日 09:57:34
 * @since
 */
@Service
public class ShopifyPipeline implements Pipeline {

    Map<String, String> urlsMap = Maps.newConcurrentMap();
    private Map<String, GoogleItem> urlItemMap = Maps.newConcurrentMap();

//    private List<GoogleItem> items = Lists.newArrayList();
    private List<GoogleItem> items = Collections.synchronizedList(new ArrayList<>());;

    public Map<String, String> getUrlsMap() {
        return urlsMap;
    }

    public void setUrlsMap(Map<String, String> urlsMap) {
        this.urlsMap = urlsMap;
    }

    public Map<String, GoogleItem> getUrlItemMap() {
        return urlItemMap;
    }

    public void setUrlItemMap(Map<String, GoogleItem> urlItemMap) {
        this.urlItemMap = urlItemMap;
    }

    public List<GoogleItem> getItems() {
        return items;
    }

    public void setItems(List<GoogleItem> items) {
        this.items = items;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        String title = resultItems.get("title");
        String description = resultItems.get("description");
        String url = resultItems.get("url");

        String origUrl = urlsMap.get(url);

        GoogleItem googleItem = new GoogleItem();
        BeanUtils.copyProperties(urlItemMap.get(origUrl), googleItem);
        googleItem.setLink(url);
        googleItem.setTitle(title);
        googleItem.setDescription(description);
        items.add(googleItem);
    }
}
