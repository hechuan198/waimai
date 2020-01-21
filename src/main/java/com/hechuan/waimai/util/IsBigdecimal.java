package com.hechuan.waimai.util;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 判断是否符合金额的正则表达式
 */
public class IsBigdecimal {

    public static boolean isBigdecimal(BigDecimal bigDecimal){

        String message = String.valueOf(bigDecimal);
        Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");
        Matcher matcher = pattern.matcher(message);
        if (matcher.matches() == false){
            return false;
        }
        return true;
    }
}
