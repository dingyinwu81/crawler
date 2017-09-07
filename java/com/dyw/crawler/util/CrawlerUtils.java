package com.dyw.crawler.util;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

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
        setHead(httpMethod, null);
    }

    /**
     * http请求设置自定义消息头
     *
     * @param httpMethod http请求方法
     * @param map        消息头
     */
    private static void setHead(HttpMethod httpMethod, Map<String, String> map) {
        //判断是否传入自定义消息头
        if (null != map && map.size() > 0) {
            map.keySet().forEach(key -> httpMethod.setRequestHeader(key, map.get(key)));
        }
        //公共消息头(不同的网站消息头内容不一致)
        httpMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");
        httpMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        httpMethod.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
    }


    /**
     * post方法设置登陆用户信息
     *
     * @param postMethod post方法
     * @param loginInfo  消息体
     */
    private static void setBody(PostMethod postMethod, NameValuePair[] loginInfo) {
        postMethod.setRequestBody(loginInfo);
    }

    /**
     * 获取html内容转成string输出（get方法）
     *
     * @param url url链接
     * @return 整个网页转成String字符串
     */
    public static String get(String url) throws Exception {
        return get(url, null);
    }

    /**
     * 获取html内容转成string输出（get方法）有自带的消息头
     *
     * @param url url链接
     * @param map 消息头内容
     * @return 整个网页转成String字符串
     */
    public static String get(String url, Map<String, String> map) throws Exception {
        String html = null;
        HttpClient httpClient = new HttpClient();
        HttpMethod httpMethod = new GetMethod(url);
        setHead(httpMethod, map);
        int status = httpClient.executeMethod(httpMethod);
        if (status == HttpStatus.SC_OK) {
            html = httpMethod.getResponseBodyAsString();
        }
        return html;
    }

    /**
     * 获取文件流（get方法）
     *
     * @param urlStr url地址
     * @return InputStream
     */
    public static InputStream downLoadFromUrl(String urlStr) throws IOException {
        return downLoadFromUrl(urlStr, null);
    }

    /**
     * 获取文件流（get方法）
     *
     * @param urlStr url地址
     * @param map    消息头内容
     * @return InputStream
     */
    public static InputStream downLoadFromUrl(String urlStr, Map<String, String> map) throws IOException {
        //通过httpclient来代替urlConnection
//        HttpHost proxy=new HttpHost("116.226.217.54", 9999);
        HttpClient httpClient = new HttpClient();
        HttpMethod httpMethod = new GetMethod(urlStr);
//        HostConfiguration hostConfiguration = new HostConfiguration();
//        hostConfiguration.setHost("116.226.217.54", 9999);
//        httpClient.setHostConfiguration(hostConfiguration);
        setHead(httpMethod, map);
        int status = httpClient.executeMethod(httpMethod);
        InputStream responseBodyAsStream = null;
        if (status == HttpStatus.SC_OK) {
            responseBodyAsStream = httpMethod.getResponseBodyAsStream();
        }
        return responseBodyAsStream;
    }


    /**
     * 登陆方法，获取cookie（post方法）
     *
     * @param url url链接
     * @return cookie
     */
    public static String post(String url, NameValuePair[] loginInfo) throws Exception {
        HttpClient httpClient = new HttpClient();
        // 模拟登陆，按实际服务器端要求选用Post请求方式
        PostMethod postMethod = new PostMethod(url);
        setHead(postMethod);
        setBody(postMethod, loginInfo);
        httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
        httpClient.executeMethod(postMethod);
        // 获得登陆后的 Cookie
        Cookie[] cookies = httpClient.getState().getCookies();
        StringBuffer cookie = new StringBuffer();
        for (Cookie c : cookies) {
            cookie.append(c.toString() + ";");
        }
        return cookie.toString();
    }
}
