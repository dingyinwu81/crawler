package com.dyw.crawler.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * 爬虫工具类
 * Created by dyw on 2017/9/1.
 */
public class CrawlerUtils {

    /**
     * 获取html内容转成string输出。
     *
     * @param url url链接
     * @return 整个网页转成String字符串
     */
    public static String getHtml(String url) throws Exception {
        URL url1 = new URL(url);//使用java.net.URL
        URLConnection connection = url1.openConnection();//打开链接
        InputStream in = connection.getInputStream();//获取输入流
        InputStreamReader isr = new InputStreamReader(in);//流的包装
        BufferedReader br = new BufferedReader(isr);

        String line;
        StringBuffer sb = new StringBuffer();
        while ((line = br.readLine()) != null) {//整行读取
            sb.append(line, 0, line.length());//添加到StringBuffer中
            sb.append('\n');//添加换行符
        }
        //关闭各种流，先声明的后关闭
        br.close();
        isr.close();
        in.close();
        return sb.toString();
    }

    /**
     * 下载文件流
     * @param urlStr url地址
     * @return InputStream
     */
    public static InputStream downLoadFromUrl(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        //设置超时间为3秒
        conn.setConnectTimeout(3 * 1000);
        conn.setRequestProperty("Accept",
                "image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/vnd.ms-powerpoint, application/vnd.ms-excel, application/msword, */*");
        conn.setRequestProperty("Accept-Language", "zh-cn");
        conn.setRequestProperty("UA-CPU", "x86");
        conn.setRequestProperty("Accept-Encoding", "gzip");//为什么没有deflate呢
        conn.setRequestProperty("Content-type", "text/html");
        conn.setRequestProperty("Connection", "keep-alive");
        //得到输入流
        return conn.getInputStream();
    }
}
