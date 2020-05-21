package com.puffer.shopify.common.util;

import com.google.common.collect.Maps;
import com.puffer.shopify.common.constants.PatternConstants;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具
 *
 * @author puffer
 * @date 2019年10月21日 20:25:21
 * @since 1.0.0
 */
public class PatterUtil {
    private static Map<String, Pattern> patternMap = Maps.newConcurrentMap();

    public static String findSpu(String txt) {
        Pattern pattern = instance(PatternConstants.SPU);
        Matcher matcher = pattern.matcher(txt);
        if (!matcher.find()) {
            return "";
        }
        String value = matcher.group().replace(PatternConstants.SPU_PREFFIX, "");
        return value;
    }

    public static Pattern instance(String pattern) {

        if (patternMap.containsKey(pattern)) {
            return patternMap.get(pattern);
        }

        Pattern compile = Pattern.compile(pattern);

        patternMap.put(pattern, compile);

        return compile;
    }
}
