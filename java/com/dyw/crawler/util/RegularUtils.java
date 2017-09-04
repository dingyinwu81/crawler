package com.dyw.crawler.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 * Created by dyw on 2017/9/1.
 */
public class RegularUtils {
    //获取img标签正则
    private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
    //获取href正则
    private static final String AURL_REG = "href=\"(.*?)\"";
    //获取http开头，png|jpg|bmp|gif结尾的 正则
    private static final String IMGSRC_REG = "[a-zA-z]+://[^\\s].*(?:png|jpg|bmp|gif)";

    /**
     * 获取 A 标签的正则表达式
     *
     * @param html 匹配的内容
     * @return List结果集
     */
    public static List<String> getAUrl(String html) {
        return match(AURL_REG, html);
    }

    /**
     * 获取 IMG 标签的正则表达式
     *
     * @param html 匹配的内容
     * @return List结果集
     */
    public static List<String> getIMGUrl(String html) {
        List<String> imgUrl = match(IMGURL_REG, html);
        return match(IMGSRC_REG, imgUrl);
    }
    /**
     * 获取 A 标签的正则表达式
     *
     * @param html 匹配的内容
     * @return List结果集
     */
    public static List<String> getIMGSrc(String html) {
        return match(IMGSRC_REG, html);
    }

    /**
     * String匹配正则，封装到list中
     *
     * @param regular 正则表达式
     * @param html    匹配的内容
     * @return 匹配到的结果 List
     */
    private static List<String> match(String regular, String html) {
        Matcher matcher = Pattern.compile(regular).matcher(html);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }

    /**
     * list匹配正则，封装到list中
     *
     * @param regular 正则表达式
     * @param list    匹配的列表
     * @return 匹配到的结果 List
     */
    private static List<String> match(String regular, List<String> list) {
        List<String> result = new ArrayList<>();
        list.forEach(string -> {
            Matcher matcher = Pattern.compile(regular).matcher(string);
            while (matcher.find()) {
                result.add(matcher.group());
            }
        });
        return result;
    }
}
