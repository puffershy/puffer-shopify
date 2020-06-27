package com.puffer.shopify.mapper;

import com.puffer.shopify.entity.KeywordDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KeywordDao {
    /**
     * 批量保存
     *
     * @param keywordDOS
     * @return void
     * @author puffer
     * @date 2020年06月26日 22:18:49
     * @since 1.0.0
     */

    void saveList(@Param("list") List<KeywordDO> keywordDOS);

    /**
    * 准备
    *
    *@author puffer
    *@date 2020年06月26日 22:34:53
    *@since 1.0.0
     * @param
    *@return int
    */

    int queryCount();

    List<KeywordDO> queryList(@Param("index") int index, @Param("size") int size);
}
