package cn.caohangwei.dmlive.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    private static Pattern linePattern = Pattern.compile("_(\\w)");

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    public static String lineToHump(String str){
        if("".equals(str) || null == str){
            return str;
        }
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            matcher.appendReplacement(sb,matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        str = sb.toString();
        str = str.substring(0,1).toUpperCase() + str.substring(1);
        return str;
    }

}
