package com.dyw.crawler.project;

import com.dyw.crawler.util.CrawlerUtils;
import org.apache.commons.httpclient.NameValuePair;

import java.util.HashMap;
import java.util.Map;

/**
 * 模拟登陆
 * Created by dyw on 2017/9/5.
 */
public class Project2 {

    public static void main(String[] args) {
        // 1   Url 开源中国网站登录url
        String loginUrl = "https://www.oschina.net/action/user/hash_login?from=";
        //个人私信网站，登录才能进入
        String dataUrl = "https://my.oschina.net/u/3673710/admin/inbox";
        // 设置登陆时要求的信息，用户名和密码
        NameValuePair[] loginInfo = {new NameValuePair("email", "填写账号"),
                new NameValuePair("pwd", "填写密码")};
        try {
            String cookie = CrawlerUtils.post(loginUrl, loginInfo);
            Map<String, String> map = new HashMap<>();
            map.put("Cookie", cookie);
            String html = CrawlerUtils.get(dataUrl, map);
            System.out.println(html);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
