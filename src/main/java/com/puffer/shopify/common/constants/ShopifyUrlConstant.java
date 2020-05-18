package com.puffer.shopify.common.constants;

public class ShopifyUrlConstant {

    /**
     * 产品接口
     * actions may be available:
     * <p>
     * GET /admin/api/2020-04/products.json
     * Retrieves a list of products
     * POST /admin/api/2020-04/products.json
     * Creates a new product
     * <p>
     * https://shopify.dev/docs/admin-api/rest/reference/products/product?api[version]=2020-04
     */
    public static final String PRODUCT_ADD = "/products.json";

    /**
     * GET /admin/api/2020-04/products/count.json
     * Retrieves a count of products
     *
     * @author puffer
     * @date 2020年05月18日 17:23:38
     * @param null
     * @return
     * @return null
     * @since 9.3.4
     */
    public static final String PRODUCT_COUNT = "/products/count.json";

    /**
     * GET /admin/api/2020-04/products/#{product_id}.json
     * Retrieves a single product
     * PUT /admin/api/2020-04/products/#{product_id}.json
     * Updates a product
     * DELETE /admin/api/2020-04/products/#{product_id}.json
     * Deletes a product
     */
    public static final String PRODUCT_SINGLE = "/products/%s.json";
}