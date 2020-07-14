package com.puffer.shopify.spider.processor;

import com.alibaba.fastjson.JSON;
import com.puffer.core.log.Log;
import com.puffer.shopify.common.constants.ShopifyConstant;
import com.puffer.shopify.common.enums.ProductFlowStateEnum;
import com.puffer.shopify.common.enums.ProductMaterialEnum;
import com.puffer.shopify.common.enums.ProductStateEnum;
import com.puffer.shopify.common.enums.ProductTypeEnum;
import com.puffer.shopify.common.enums.YesNoEnum;
import com.puffer.shopify.common.util.AmazonPageUtil;
import com.puffer.shopify.entity.ProductDO;
import com.puffer.shopify.entity.ProductImageDO;
import com.puffer.shopify.entity.ProductRankDO;
import com.puffer.shopify.service.ThreadLocalService;
import com.puffer.shopify.vo.ProductExcelVO;
import com.puffer.shopify.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 亚马逊爬虫
 *
 * @author puffer
 * @date 2019年10月13日 19:32:56
 * @since 1.0.0
 */
@Slf4j
@Service
public class AmazonPageProcessor implements PageProcessor {

    private Site site = Site.me().setTimeOut(10000).setRetryTimes(3).setSleepTime(1000);
    //    private static final String LIST_URL_PATTER = "(.*Best-Sellers.* |)|(.*new-releases.*)";

    private List<String> spuList = Lists.newArrayList();

    public void setSpuList(List<String> spuList) {
        this.spuList.addAll(spuList);
    }

    @Resource
    private ThreadLocalService threadLocalService;

    @Override
    public void process(Page page) {
        if (AmazonPageUtil.isListPage(page)) {
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
        final String op = "AmazonPageProcessor.buildProduct";
        //step1. 构建产品信息
        ProductDO productDO = buildProductDO(page);
        if (productDO == null || spuList.contains(productDO.getSpu())) {
            log.info(Log.newInstance(op, "该产品已经爬过程，跳过").kv("productDO", JSON.toJSONString(productDO)).toString());
            page.setSkip(true);
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
        final String op = "AmazonPageProcessor.buildProductDO";
        String title = AmazonPageUtil.queryTitle(page);
        String url = page.getUrl().toString();
        String spu = AmazonPageUtil.querySpu(page);
        BigDecimal price = AmazonPageUtil.queryPrice(page);
        String description = AmazonPageUtil.queryDescription(page);

        if (price == null) {
            ProductExcelVO productExcelVO = threadLocalService.queryProductExcel(url);
            if (productExcelVO == null || productExcelVO.getPrice() == null) {
                log.info(Log.newInstance(op, "price为空，跳过改产品").kv("url", url).toString());
                page.setSkip(true);
                return null;
            }

            price = productExcelVO.getPrice();
        }

        if (StringUtils.isBlank(title) || StringUtils.isBlank(spu)) {
            log.info(Log.newInstance(op, "标题为空，或者spu为空，price为空，跳过改产品").kv("url", url).toString());
            page.setSkip(true);
            return null;
        }

        boolean freeShipping = AmazonPageUtil.queryFreeShipping(page);

        ProductDO productDO = new ProductDO();
        productDO.setSpu(spu);
        productDO.setType(ProductTypeEnum.COFFEE_MUG.getValue());
        productDO.setMaterial(ProductMaterialEnum.CERAMIC.getValue());
        productDO.setTitle(title);
        productDO.setAmazonPrice(price);
        productDO.setShopifyPrice(price.add(ShopifyConstant.ADD_AMAZON_PRICE));
        productDO.setUrl(url);
        productDO.setFreeShipping(freeShipping ? YesNoEnum.YES.getValue() : YesNoEnum.NO.getValue());
        //        productDO.setShopifyPrice();
        //        productDO.setProductId();
        productDO.setFlowState(ProductFlowStateEnum.TO_UPDATE_TITLE.getValue());

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
