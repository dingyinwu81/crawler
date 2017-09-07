package com.dyw.crawler.project;

import com.dyw.crawler.util.CrawlerUtils;
import com.dyw.crawler.util.DownloadTask;
import com.dyw.crawler.util.IOUtils;
import com.dyw.crawler.util.RegularUtils;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * 多线程下载图片
 * Created by dyw on 2017/9/7.
 */
public class Project3 {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        String path = "C:\\Users\\dyw\\Desktop\\crawler\\photo";
        String path1 = "C:\\Users\\dyw\\Desktop\\crawler\\photo1";
        String url = "http://www.tuigirlba.cc/page/show/";
        List<String> list = new ArrayList<>();
        try {
            for (int i = 300; i < 400; i++) {
                String htmlContent = CrawlerUtils.get(url + i);
                List<String> imgUrls = RegularUtils.getIMGUrl(htmlContent);
                list.addAll(imgUrls);
            }
            long l = System.currentTimeMillis();
            forkJoinPool.execute(new DownloadTask(list, 0, list.size(), path));
            forkJoinPool.shutdown();
            forkJoinPool.awaitTermination(20, TimeUnit.SECONDS);
            long l1 = System.currentTimeMillis() - l;
            long l2 = System.currentTimeMillis();
            list.forEach(imgUrl -> {
                String[] split = imgUrl.split("/");
                String imgName = split[split.length - 1];
                try {
                    File file1 = new File(path1 + "/" + imgName);
                    InputStream inputStream = CrawlerUtils.downLoadFromUrl(imgUrl);
                    IOUtils.saveFile(inputStream, file1);
                    System.out.println("success:" + imgUrl);
                } catch (Exception e) {
                    System.out.println("fail:" + imgUrl);
                }
            });
            long l3 = System.currentTimeMillis() - l2;
            System.out.println("forkjoin处理时间："+l1);
            System.out.println("没有并行处理时间："+l3);
        } catch (Exception e) {
            throw new RuntimeException("获取内容失败!", e);
        }
    }
}
