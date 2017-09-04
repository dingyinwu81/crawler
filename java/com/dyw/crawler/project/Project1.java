package com.dyw.crawler.project;

import com.dyw.crawler.util.CrawlerUtils;
import com.dyw.crawler.util.IOUtils;
import com.dyw.crawler.util.RegularUtils;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * 下载网页中的图片
 * Created by dyw on 2017/9/4.
 */
public class Project1 {
    public static void main(String[] args) {
        //文件放置的路径
        String path = "C:\\Users\\dyw\\Desktop\\crawler\\photo";
        //爬取的网站地址
        String url = "http://blog.csdn.net/juewang_love";
        //获取内容
        String htmlContent = null;
        try {
            htmlContent = CrawlerUtils.getHtml(url);
        } catch (Exception e) {
            throw new RuntimeException("获取内容失败!", e);
        }
        //获取所有的img的内容
        List<String> imgUrls = RegularUtils.getIMGUrl(htmlContent);
        //分别下载每个img
        imgUrls.forEach(imgUrl -> {
            String[] split = imgUrl.split("/");
            String imgName = split[split.length - 1];
            try {
                File file1 = new File(path + "/" + imgName);
                InputStream inputStream = CrawlerUtils.downLoadFromUrl(imgUrl);
                IOUtils.saveFile(inputStream, file1);
                System.out.println("success:" + imgName);
            } catch (Exception e) {
                System.out.println("fail:" + imgUrl);
            }
        });
    }
}
