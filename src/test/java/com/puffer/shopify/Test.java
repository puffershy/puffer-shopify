package com.puffer.shopify;

import com.puffer.shopify.common.constants.PatternConstants;
import com.puffer.shopify.common.util.PatterUtil;
import com.puffer.util.regex.RegularUtil;
import org.springframework.util.PatternMatchUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static void main(String[] args) {
        String str = "\uD83D\uDE0D AMAZING GIFT - For Sea Creature Lovers , this Octopus Coffee Mug is sure to get an amazing reaction. A hand painted mug gift for your daughter, wife, mother or coworker on their Birthday, Christmas or Valentines";
//        Pattern emoji = Pattern.compile (PatternConstants.EMOJI,Pattern.UNICODE_CASE | Pattern . CASE_INSENSITIVE ) ;
        Pattern emoji =PatterUtil.instance(PatternConstants.EMOJI);



        Matcher emojiMatcher = emoji.matcher(str);

        if ( emojiMatcher.find())
        {
            str = emojiMatcher.replaceAll("");
            System.out.println(str);
        }
    }
}