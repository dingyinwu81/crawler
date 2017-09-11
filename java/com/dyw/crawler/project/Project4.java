package com.dyw.crawler.project;

import com.dyw.crawler.util.ConnectionPool;
import com.dyw.crawler.util.CrawlerUtils;
import com.dyw.crawler.util.IOUtils;
import com.dyw.crawler.util.RegularUtils;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * 保存爬虫的文件信息保存到数据库
 * Created by dyw on 2017/9/11.
 */
public class Project4 {

    private static String url = "jdbc:mysql://localhost:3306/crawler?characterEncoding=utf8&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private static String username = "root";

    private static String password = "root";

    private static String driver = "com.mysql.jdbc.Driver";

    public static void main(String[] args) throws Exception {
        // 创建数据库连接池对象
        ConnectionPool connPool = new ConnectionPool(driver, url, username, password);
        // 新建数据库连接
        connPool.createPool();
        // SQL
        String insertSql = "insert into img (url,file_name,create_date,path) values (?,?,?,?)";
        //文件放置的路径
        String path = "C:\\Users\\dyw\\Desktop\\crawler\\photo5";
        //爬取的网站地址
        String url = "http://www.gravuregirlz.com/145741/song-guo-%E6%9D%BE%E6%9E%9C%E5%84%BFcc-naked-uncensored-unreleased-tuigirl-sets/";
        //获取内容
        String htmlContent = null;
        try {
            htmlContent = CrawlerUtils.get(url);
        } catch (Exception e) {
            throw new RuntimeException("获取内容失败!", e);
        }
        //获取所有的img的内容
        List<String> imgUrls = RegularUtils.getIMGUrl(htmlContent);
        //分别下载每个img
        imgUrls.forEach(imgUrl -> {
            if (!imgUrl.startsWith("http")) {
                imgUrl = url + imgUrl;
            }
            String[] split = imgUrl.split("/");
            String imgName = split[split.length - 1];
            try {
                File file1 = new File(path + "/" + imgName);
                InputStream inputStream = CrawlerUtils.downLoadFromUrl(imgUrl);
                IOUtils.saveFile(inputStream, file1);
                System.out.println("success:" + imgUrl);
                // 从连接库中获取一个可用的连接
                Connection conn = connPool.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(insertSql);
                preparedStatement.setString(new Integer(1), imgUrl);
                preparedStatement.setString(new Integer(2), imgName);
                preparedStatement.setDate(new Integer(3), new Date(System.currentTimeMillis()));
                preparedStatement.setString(new Integer(4), path);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connPool.returnConnection(conn); // 连接使用完后释放连接到连接池
            } catch (Exception e) {
                System.out.println("fail:" + imgUrl);
            }
        });
    }
}
