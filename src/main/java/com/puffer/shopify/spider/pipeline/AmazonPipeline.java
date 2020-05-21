package com.puffer.shopify.spider.pipeline;

import com.puffer.shopify.mapper.ProductDao;
import com.puffer.shopify.mapper.ProductImageDao;
import com.puffer.shopify.mapper.ProductRankDao;
import com.puffer.shopify.mapper.ProductVariantDao;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;

/**
 * 亚马逊商品信息持久化
 *
 * @author puffer
 * @date 2019年10月14日 22:47:47
 * @since 1.0.0
 */
@Component
public class AmazonPipeline implements Pipeline {

    @Resource
    private ProductDao productDao;

    @Resource
    private ProductRankDao productRankDao;

    @Resource
    private ProductImageDao productImageDao;

    @Resource
    private ProductVariantDao productVariantDao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        //TODO 保存实体
        // AmazonItem amazonItem = new AmazonItem();
        // amazonItem.setType(ProductTypeEnum.BALLON.name());
        // amazonItem.setTitle(resultItems.get("title"));
        // amazonItem.setSpu(resultItems.get("spu"));
        // amazonItem.setPrice(new BigDecimal(resultItems.get("price").toString()));
        // amazonItem.setUrl(resultItems.get("url"));
        //
        // amazonItem.setRank(StringUtils.isNotBlank(resultItems.get("rank")) ? Integer.valueOf(resultItems.get("rank")) : null);
        //
        // amazonItem.setDescription(resultItems.get("description"));
        // amazonItem.setImageUrl(resultItems.get("imageUrl"));
        //
        // amazonItemDao.save(amazonItem);
    }

}
