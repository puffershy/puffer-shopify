package com.puffer.shopify.service;

import com.puffer.core.log.Log;
import com.puffer.shopify.common.constants.ConfigConstant;
import com.puffer.shopify.common.enums.KeywordTypeEnum;
import com.puffer.shopify.entity.KeywordDO;
import com.puffer.shopify.mapper.KeywordDao;
import com.puffer.util.lang.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class KeywordsService {

    private Set<String> keySet = Sets.newHashSet();

    @Resource
    private KeywordDao keywordDao;

    public void saveCompite(List<String> fileList, KeywordTypeEnum keywordTypeEnum) throws IOException {
        final String op = "KeywordsService.saveCompite";
        log.info(Log.newInstance(op, "更新关键词库").kv("keywordTypeEnum", keywordTypeEnum.toString()).toString());
        initKeywords();

        for (String s : fileList) {
            saveKeywords(s, keywordTypeEnum);
        }
    }

    /**
     * 保存关键字
     *
     * @param filePath
     * @param keywordTypeEnum
     * @author puffer
     * @date 2020年06月26日 21:58:55
     * @since 1.0.0
     */
    public void saveKeywords(String filePath, KeywordTypeEnum keywordTypeEnum) throws IOException {
        List<KeywordDO> keywordDOS = Lists.newArrayList();

        List<KeywordDO> updateKeywordList = Lists.newArrayList();
        int j = 0;

        List<Object[]> objects = ExcelUtil.readFile(filePath, 3);
        for (Object[] object : objects) {

            String keyword = String.valueOf(object[0]).trim();

            int i = 0;
            if (object.length >= 3 && object[2] != null && StringUtils.isNotBlank(object[2].toString())) {
                i = new Double(object[2].toString()).intValue();
            }

            if (keySet.contains(keyword)) {
                //如果已经存在，则更新

                KeywordDO keywordDO = new KeywordDO();
                keywordDO.setKeyword(keyword);
                keywordDO.setSearches(i);
                keywordDO.setType(keywordTypeEnum.getValue());
                updateKeywordList.add(keywordDO);
                continue;
            } else {
                keySet.add(keyword);
            }

            KeywordDO keywordDO = new KeywordDO();
            keywordDO.setKeyword(keyword);
            keywordDO.setSearches(i);
            keywordDO.setType(keywordTypeEnum.getValue());
            keywordDOS.add(keywordDO);

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
                keySet.add(keywordDO.getKeyword());
            }
        }

    }
}