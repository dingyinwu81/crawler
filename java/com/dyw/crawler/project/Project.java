package com.dyw.crawler.project;

import com.dyw.crawler.util.CrawlerUtils;
import com.dyw.crawler.util.IOUtils;

import java.io.File;

/**
 * 下载html内容
 * Created by dyw on 2017/9/1.
 */
public class Project {
    public static void main(String[] args) {
        //文件放置的路径
        String path = "C:\\Users\\dyw\\Desktop\\crawler";
        //爬取的网站地址
        String url = "http://www.mmjpg.com/";
        String fileRealName = path + "/index.html";
        File file = new File(fileRealName);
        //创建文件
        try {
            IOUtils.createFile(file);
        } catch (Exception e) {
            throw new RuntimeException("创建文件失败!", e);
        }
        //获取内容
        String htmlContent = null;
        try {
            htmlContent = CrawlerUtils.getHtml(url);
        } catch (Exception e) {
            throw new RuntimeException("获取内容失败!", e);
        }
        //写入内容
        try {
            IOUtils.writeFile(htmlContent, file);
        } catch (Exception e) {
            throw new RuntimeException("内容写入文件失败!", e);
        }
    }


}
