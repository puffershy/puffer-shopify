package com.puffer.shopify.vo;

import com.puffer.shopify.entity.KeywordDO;
import com.puffer.shopify.entity.KeywordTagDO;
import lombok.Data;

import java.util.List;

/**
 * 关键词快照类
 *
 * @author buyi
 * @date 2020年07月14日 17:13:22
 * @since 1.0.0
 */
@Data
public class KeywordVO {
    private KeywordDO keywordDO;
    private List<KeywordTagDO> keywordTagDOList;

    public KeywordVO(KeywordDO keywordDO, List<KeywordTagDO> keywordTagDOList) {
        this.keywordDO = keywordDO;
        this.keywordTagDOList = keywordTagDOList;
    }

    public static KeywordVO newInstance(KeywordDO keywordDO, List<KeywordTagDO> keywordTagDOList) {
        return new KeywordVO(keywordDO, keywordTagDOList);
    }
}
