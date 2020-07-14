package com.puffer.shopify.mapper;

import com.puffer.shopify.entity.KeywordTagDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KeywordTagDao {
    int insert(KeywordTagDO record);

    void saveList(@Param("list") List<KeywordTagDO> keywordTagDOList);

    List<KeywordTagDO> queryByKeywordId(@Param("keywordId") Long keywordId);
}