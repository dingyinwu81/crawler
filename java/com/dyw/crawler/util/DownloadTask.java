package com.dyw.crawler.util;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * forkJoin pool 并行处理下载图片
 * Created by dyw on 2017/9/7.
 */
public class DownloadTask extends RecursiveAction {
    //每个任务总数
    private static final int THRESHOLD = 8;
    //传入的所有的url的列表
    private List<String> urls;
    //开始坐标
    private int start;
    //结束坐标
    private int end;
    //保存路径
    private String path;

    /**
     * @param urls  url集合
     * @param start 开始坐标
     * @param end   结束坐标
     * @param path  保存路径
     */
    public DownloadTask(List<String> urls, int start, int end, String path) {
        this.urls = urls;
        this.start = start;
        this.end = end;
        this.path = path;
    }

    @Override
    protected void compute() {
        if (end - start < THRESHOLD) {
            for (int i = start; i < end; i++) {
                String url = urls.get(i);
                String[] split = url.split("/");
                String imgName = split[split.length - 1];
                try {
                    File file = new File(path + "/" + imgName);
                    InputStream inputStream = CrawlerUtils.downLoadFromUrl(url);
                    IOUtils.saveFile(inputStream, file);
                    System.out.println("success:" + url);
                } catch (Exception e) {
                    System.out.println("fail:" + url);
                }
            }
        } else {
            // 如果当end与start之间的差大于THRESHOLD时,将大任务分解成两个小任务。
            int middle = (start + end) / 2;
            DownloadTask left = new DownloadTask(urls, start, middle, path);
            DownloadTask right = new DownloadTask(urls, middle, end, path);
            // 并行执行两个“小任务”
            left.fork();
            right.fork();
        }
    }
}
