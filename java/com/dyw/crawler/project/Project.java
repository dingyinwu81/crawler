package com.dyw.crawler.project;

import com.dyw.crawler.util.CrawlerUtils;
import com.dyw.crawler.util.IOUtils;

import java.io.File;

/**
 * 此包中的main方法
 * Created by dyw on 2017/9/1.
 */
public class Project {

    public static void main(String[] args) {
        String path = "C:\\Users\\dyw\\Desktop\\crawler";
        String url = "http://www.cnblogs.com/xxyBlogs/p/6151644.html";
        String fileRealName = path + "/index.html";
        File file = new File(fileRealName);
        try {
            IOUtils.createFile(file);
        } catch (Exception e) {
            throw new RuntimeException("创建文件失败!",e);
        }
        String html = null;
        try {
            html = CrawlerUtils.getHtml(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            IOUtils.writeFile(html,file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
