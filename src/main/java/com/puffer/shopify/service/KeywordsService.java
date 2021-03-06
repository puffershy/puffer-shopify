package com.puffer.shopify.service;

import com.google.common.collect.Maps;
import com.puffer.core.log.Log;
import com.puffer.shopify.common.constants.ConfigConstant;
import com.puffer.shopify.common.enums.ForTypeEnum;
import com.puffer.shopify.entity.KeywordDO;
import com.puffer.shopify.entity.KeywordTagDO;
import com.puffer.shopify.mapper.KeywordDao;
import com.puffer.shopify.mapper.KeywordTagDao;
import com.puffer.shopify.vo.KeywordVO;
import com.puffer.util.lang.ExcelUtil;
import com.puffer.util.lang.GenerateIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class KeywordsService {
    private Map<String, KeywordVO> keywordDOMap = Maps.newConcurrentMap();

    @Resource
    private KeywordDao keywordDao;

    @Resource
    private KeywordTagDao keywordTagDao;

    public void saveCompite(List<String> fileList, String keywordTag) throws IOException {
        final String op = "KeywordsService.saveCompite";
        log.info(Log.newInstance(op, "更新关键词库").kv("keywordTagEnum", keywordTag).toString());
        initKeywords();

        for (String s : fileList) {
            saveKeywords(s, keywordTag);
        }
    }

    /**
     * 保存关键字
     *
     * @param filePath
     * @param keywordTag
     * @author puffer
     * @date 2020年06月26日 21:58:55
     * @since 1.0.0
     */
    public void saveKeywords(String filePath, String keywordTag) throws IOException {
        List<KeywordDO> keywordDOS = Lists.newArrayList();
        List<KeywordTagDO> keywordTagDOS = Lists.newArrayList();

        List<KeywordDO> updateKeywordList = Lists.newArrayList();

        int j = 0;

        List<Object[]> objects = ExcelUtil.readFile(filePath, 3);
        for (Object[] object : objects) {

            String keyword = String.valueOf(object[0]).trim();

            int i = 0;
            if (object.length >= 3 && object[2] != null && StringUtils.isNotBlank(object[2].toString())) {
                i = new Double(object[2].toString()).intValue();
            }

            if (keywordDOMap.containsKey(keyword)) {
                //如果已经存在，则更新
                KeywordVO keywordVO = keywordDOMap.get(keyword);
                KeywordDO keywordDO = keywordVO.getKeywordDO();
                keywordDO.setKeyword(keyword);
                keywordDO.setSearches(i);
                updateKeywordList.add(keywordDO);

                List<KeywordTagDO> keywordTagDOList = keywordVO.getKeywordTagDOList();
                if (CollectionUtils.isEmpty(keywordTagDOList)) {
                    continue;
                }

                boolean tagFlag = true;
                for (KeywordTagDO keywordTagDO : keywordTagDOList) {
                    if (keywordTagDO.getCode().equals(keywordTag)) {
                        tagFlag = false;
                        continue;
                    }
                }

                if (tagFlag) {
                    KeywordTagDO keywordTagDO = new KeywordTagDO();
                    keywordTagDO.setKeywordId(keywordDO.getId());
                    keywordTagDO.setCode(keywordTag);
                    keywordTagDOS.add(keywordTagDO);
                }
                continue;
            }

            KeywordDO keywordDO = new KeywordDO();
            keywordDO.setId(GenerateIdUtil.generateId());
            keywordDO.setKeyword(keyword);
            keywordDO.setSearches(i);

            keywordDOS.add(keywordDO);

            KeywordTagDO keywordTagDO = new KeywordTagDO();
            keywordTagDO.setKeywordId(keywordDO.getId());
            keywordTagDO.setCode(keywordTag);

            keywordTagDOS.add(keywordTagDO);

            keywordDOMap.put(keyword, KeywordVO.newInstance(keywordDO, Lists.newArrayList(keywordTagDO)));

            if (keywordDOS.size() == ConfigConstant.QUERY_SIZE) {
                keywordDao.saveList(keywordDOS);
                keywordDOS.clear();
                log.info("保存关键词" + keywordDOS.size());
            }
        }

        if (!keywordDOS.isEmpty()) {
            keywordDao.saveList(keywordDOS);
            log.info("保存关键词数量" + keywordDOS.size());
        }

        if (!updateKeywordList.isEmpty()) {
            keywordDao.updateList(updateKeywordList);
            log.info("更新关键字数量" + updateKeywordList.size());
        }

        if (!keywordTagDOS.isEmpty()) {
            //过滤已经存在的tag
            keywordTagDao.saveList(keywordTagDOS);
        }

    }

    /**
     * 初始化关键词到内存
     *
     * @param
     * @return void
     * @author puffer
     * @date 2020年06月26日 22:40:00
     * @since 1.0.0
     */

    public void initKeywords() {
        int count = keywordDao.queryCount();
        int batchSize = (count - 1) / ConfigConstant.QUERY_SIZE + 1;
        for (int i = 0; i < batchSize; i++) {
            List<KeywordDO> keywordDOS = keywordDao.queryList(i * ConfigConstant.QUERY_SIZE, ConfigConstant.QUERY_SIZE);
            for (KeywordDO keywordDO : keywordDOS) {
                List<KeywordTagDO> keywordTagDOList = keywordTagDao.queryByKeywordId(keywordDO.getId());
                keywordDOMap.put(keywordDO.getKeyword(), KeywordVO.newInstance(keywordDO, keywordTagDOList));
            }
        }
    }

    public String queryKeyword(ForTypeEnum forType) {
        return "";
    }
}