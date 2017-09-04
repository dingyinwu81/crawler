package com.dyw.crawler.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 爬虫工具类
 * Created by dyw on 2017/9/1.
 */
public class CrawlerUtils {

    /**
     * http请求设置消息头
     *
     * @param httpMethod http请求方法
     */
    private static void setHead(HttpMethod httpMethod) {
        httpMethod.setRequestHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        httpMethod.setRequestHeader("Content-Type", "Utf-8");
        httpMethod.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
    }

    /**
     * 获取html内容转成string输出（get方法）
     *
     * @param url url链接
     * @return 整个网页转成String字符串
     */
    public static String getHtml(String url) throws Exception {
        InputStream inputStream = downLoadFromUrl(url);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "Utf-8"));
        StringBuffer stringBuffer = new StringBuffer();
        String str;
        while ((str = br.readLine()) != null) {
            stringBuffer.append(str);
            stringBuffer.append('\n');//添加换行符
        }
        return stringBuffer.toString();
    }

    /**
     * 获取文件流（get方法）
     *
     * @param urlStr url地址
     * @return InputStream
     */
    public static InputStream downLoadFromUrl(String urlStr) throws IOException {
        //通过httpclient来代替urlConnection
        HttpClient httpClient = new HttpClient();
        HttpMethod httpMethod = new GetMethod(urlStr);
        setHead(httpMethod);
        int status = httpClient.executeMethod(httpMethod);
        InputStream responseBodyAsStream = null;
        if (status == HttpStatus.SC_OK) {
            responseBodyAsStream = httpMethod.getResponseBodyAsStream();
        }
        return responseBodyAsStream;
    }
}
