package com.dyw.crawler.file;

/**
 * 正则表达式集合类
 * Created by dyw on 2017/9/14.
 */
public class RegularCollection {

    //获取img标签正则
    public static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
    //获取href正则
    public static final String AURL_REG = "href=\"(.*?)\"";
    //获取http开头，png|jpg|bmp|gif结尾的 正则
    public static final String IMGSRC_REG = "[a-zA-z]+://[^\\s]*(?:png|jpg|bmp|gif)";
    //获取没有以http开头，png|jpg|bmp|gif结尾的 正则
    public static final String IMGSRC_REG1 = "/[^\\s]*(?:png|jpg|bmp|gif)";

    /* **************************知乎网址************************** */
    //知乎获取 question link
    public static final String ZHIHU_QUESTION_link = "_link.*target";
    //匹配知乎 question uri
    public static final String ZHIHU_QUESTION_URI = "/.*[0-9]{8}";
    //匹配标题   匹配结果：Header-title">有哪些值得一提的生活窍门？<    还得：substring(14, title.length() - 1);
    public static final String ZHIHU_TITLE = "Header-title.*?<";
    //匹配问题当前回答数    匹配结果：List-headerText"><span>344 个回答</    还得：substring(23, title.length() - 1);
    public static final String ZHIHU_ANSWER = "List-headerText.*?</";
    //匹配关注者和被浏览数    匹配结果：NumberBoard-value">4894</    还得：substring(19, title.length() - 1);
    public static final String ZHIHU_CONCERN = "NumberBoard-value.*?</";
    //匹配答案内容    匹配结果：   还得：substring(19, title.length() - 1);
    public static final String ZHIHU_ANSWER_CONTENT = "CopyrightRichText-richText\" itemprop.*?<div class=\"ContentItem-time\">";
    //匹配答案点赞数    匹配结果：   还得：substring(40, title.length() - 1);
    public static final String ZHIHU_LIKE_COUNT = "AnswerItem-extraInfo.*?</button>";
}
