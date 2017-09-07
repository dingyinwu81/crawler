package com.dyw.crawler.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * 获取
 * Created by dyw on 2017/9/7.
 */
public class GetIMGurlsTask extends RecursiveTask<List<String>> {
    //每个任务总数
    private static final int THRESHOLD = 2;
    //传入的所有的url的列表
    private List<String> urls;
    //开始坐标
    private int start;
    //结束坐标
    private int end;

    public GetIMGurlsTask(List<String> urls, int start, int end) {
        this.urls = urls;
        this.start = start;
        this.end = end;
    }

    @Override
    protected List<String> compute() {
        List<String> list = Collections.synchronizedList(new ArrayList<String>());
        // 当end与start之间的差小于THRESHOLD时，开始进行实际累加
        if (end - start < THRESHOLD) {
            for (int i = start; i < end; i++) {
                try {
                    String htmlContent = CrawlerUtils.get(urls.get(i));
                    List<String> imgUrls = RegularUtils.getIMGUrl(htmlContent);
                    list.addAll(imgUrls);
                    System.out.println(urls.get(i) + "  数量：" + imgUrls.size());
                } catch (Exception e) {
                    System.out.println(urls.get(i));
                }
            }
            return list;
        } else {
            // 将大任务分解成两个小任务。
            int middle = (start + end) / 2;
            GetIMGurlsTask left = new GetIMGurlsTask(urls, start, middle);
            GetIMGurlsTask right = new GetIMGurlsTask(urls, middle, end);
            // 并行执行两个“小任务”
            left.fork();
            right.fork();
            // 把两个“小任务”累加的结果合并起来
            list.addAll(left.join());
            list.addAll(right.join());
            return list;
        }
    }
}
