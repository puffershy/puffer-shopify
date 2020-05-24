package com.puffer.shopify.spider.processor;

import com.puffer.shopify.common.constants.AmazonConstant;
import com.puffer.shopify.common.constants.PatternConstants;
import com.puffer.shopify.common.constants.ShopifyConstant;
import com.puffer.shopify.common.enums.*;
import com.puffer.shopify.common.util.AmazonPageUtil;
import com.puffer.shopify.common.util.PatterUtil;
import com.puffer.shopify.entity.ProductDO;
import com.puffer.shopify.entity.ProductImageDO;
import com.puffer.shopify.entity.ProductRankDO;
import com.puffer.shopify.vo.ProductVO;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;

/**
 * 亚马逊爬虫
 *
 * @author puffer
 * @date 2019年10月13日 19:32:56
 * @since 1.0.0
 */
public class AmazonPageProcessor implements PageProcessor {

    private Site site = Site.me().setTimeOut(10000).setRetryTimes(3).setSleepTime(1000);
//    private static final String LIST_URL_PATTER = "(.*Best-Sellers.* |)|(.*new-releases.*)";

    @Override
    public void process(Page page) {
        if (page.getUrl().regex(PatternConstants.AMAZON_BEST_SELLER_URL).match()) {
            //如果当前页面是列表页面

            //添加所有产品的连接
            // page.addTargetRequests(page.getHtml().xpath("//*[@id=\"zg-right-col\"]").links().all());
            page.addTargetRequests(AmazonPageUtil.queryProductUrlList(page));

            //获取所有分页的连接
            // page.addTargetRequests(page.getHtml().xpath("//*[@id=\"zg-center-div\"]/div[2]/div").links().all());
            page.addTargetRequests(AmazonPageUtil.queryPageUrlList(page));
            page.setSkip(true);
        } else {

            boolean contains = page.getUrl().toString().contains("/dp/");
            if (!contains) {
                page.setSkip(true);
                return;
            }

            //构建产品信息
            buildProduct(page);
        }
    }

    private void buildProduct(Page page) {
        //step1. 构建产品信息
        ProductDO productDO = buildProductDO(page);
        if (productDO == null) {
            return;
        }

        //step2. 构建产品排行
        List<ProductRankDO> productRankDOS = AmazonPageUtil.queryRank(page, productDO.getSpu());

        //step3. 构架产品图片
        List<ProductImageDO> productImageDOS = AmazonPageUtil.queryImage(page, productDO.getSpu());

        ProductVO productVO = ProductVO.builder().productDO(productDO).productImageDOList(productImageDOS).productRankDOList(productRankDOS).build();
        page.putField("productVO", productVO);
    }

    private ProductDO buildProductDO(Page page) {
        String title = AmazonPageUtil.queryTitle(page);
        String url = page.getUrl().toString();
        String spu = AmazonPageUtil.querySpu(page);
        BigDecimal price = AmazonPageUtil.queryPrice(page);
        String description = AmazonPageUtil.queryDescription(page);

        if (StringUtils.isBlank(title) || StringUtils.isBlank(spu) || price == null) {
            page.setSkip(true);
            return null;
        }

        boolean freeShipping = AmazonPageUtil.queryFreeShipping(page);


        ProductDO productDO = new ProductDO();
        productDO.setSpu(spu);
        productDO.setType(ProductTypeEnum.COFFEE_MUG.name());
        productDO.setMaterial(ProductMaterialEnum.Ceramic.name());
        productDO.setTitle(title);
        productDO.setAmazonPrice(price);
        productDO.setShopifyPrice(price.add(ShopifyConstant.ADD_AMAZON_PRICE));
        productDO.setUrl(url);
        productDO.setFreeShipping(freeShipping ? YesNoEnum.YES.getValue() : YesNoEnum.NO.getValue());
        //        productDO.setShopifyPrice();
        //        productDO.setProductId();
        productDO.setFlowState(ProductFlowStateEnum.TO_UPLOAD.getValue());


        productDO.setState(ProductStateEnum.EFFECTIVE.getValue());


        //        productDO.setCreateTime();
        //        productDO.setUpdateTime();
        productDO.setDescription(description);

        return productDO;
    }

    @Override
    public Site getSite() {
        return site;
    }
}
