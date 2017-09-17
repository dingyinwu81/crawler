package com.dyw.crawler.project;

import com.dyw.crawler.file.ExcelTitleConllection;
import com.dyw.crawler.file.RegularCollection;
import com.dyw.crawler.file.URICollection;
import com.dyw.crawler.model.Zhihu;
import com.dyw.crawler.util.ConnectionPool;
import com.dyw.crawler.util.CrawlerUtils;
import com.dyw.crawler.util.ExcelUtils;
import com.dyw.crawler.util.RegularUtils;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 爬取知乎根话题 排行榜 前几 问题 答案等相关信息
 * Created by dyw on 2017/9/12.
 */
public class Project5 {

    public static void main(String[] args) throws Exception {

        ConnectionPool connectionPool = new ConnectionPool();
        connectionPool.createPool();
        // 网站登录url
//        String loginUrl = "https://www.zhihu.com/";
        //爬取网站
        String dataUrl = URICollection.ZHIHUTOPIC;
        // 设置登陆时要求的信息，用户名和密码
//        NameValuePair[] loginInfo = {new NameValuePair("phone_num", ""),
//                new NameValuePair("password", "")};
        try {
//            String cookie = CrawlerUtils.post(loginUrl, loginInfo);
            Map<String, String> map = new HashMap<>();
            map.put("Cookie", "z_c0=Mi4xUUpYUkJRQUFBQUFBQUFLWVhlcEZEQmNBQUFCaEFsVk5TRlRnV1FCTUt4M2QyQ1lId1NONk14cndEU0I0NGo1cVd3|1505281864|4de8d12f357bca8cb7665bbda854e46a7bc842d4");
            String html = CrawlerUtils.get(dataUrl, map);
            List<String> list = RegularUtils.match(RegularCollection.ZHIHU_QUESTION_link, html);
            List<String> uriLists = RegularUtils.match(RegularCollection.ZHIHU_QUESTION_URI, list);

            uriLists.forEach(uri -> {
                String url = URICollection.ZHIHU + uri;
                try {
                    //详细页内容
                    String detailHtml = CrawlerUtils.get(url, map);
                    //匹配标题
                    List<String> match = RegularUtils.match(RegularCollection.ZHIHU_TITLE, detailHtml);
                    String title = match.get(0);
                    title = title.substring(14, title.length() - 1);
                    //答案数
                    List<String> match1 = RegularUtils.match(RegularCollection.ZHIHU_ANSWER, detailHtml);
                    String answerCount = match1.get(0);
                    answerCount = answerCount.substring(23, answerCount.length() - 2);
                    //关注者和被浏览数
                    List<String> match2 = RegularUtils.match(RegularCollection.ZHIHU_CONCERN, detailHtml);
                    String concern = match2.get(0);
                    concern = concern.substring(19, concern.length() - 2);
                    String browsed = match2.get(1);
                    browsed = browsed.substring(19, browsed.length() - 2);
                    //答案内容
                    List<String> match3 = RegularUtils.match(RegularCollection.ZHIHU_ANSWER_CONTENT, detailHtml);
                    String answer1 = match3.get(0);
                    answer1 = answer1.substring(44, answer1.length() - 40);
                    String answer2 = match3.get(1);
                    answer2 = answer2.substring(44, answer2.length() - 40);
                    //答案内容点赞数
                    List<String> match4 = RegularUtils.match(RegularCollection.ZHIHU_LIKE_COUNT, detailHtml);
                    String like1 = match4.get(0);
                    like1 = like1.substring(94, like1.length() - 9);
                    String like2 = match4.get(1);
                    like2 = like2.substring(94, like2.length() - 9);

                    Connection conn = connectionPool.getConnection();
                    Zhihu zhihu = new Zhihu(title, "", concern, browsed, answerCount, answer1, like1, "", answer2, like2, "", "", "", "", "", "", "", "", "", "");
                    executeInsert(conn, zhihu);
                    connectionPool.returnConnection(conn);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        OutputStream out = new FileOutputStream("C:\\Users\\dyw\\Desktop\\crawler\\a.xls");
        Connection conn1 = connectionPool.getConnection();
        List<Zhihu> list = excueteQuery(conn1);
        connectionPool.returnConnection(conn1);
        ExcelUtils<Zhihu> ex = new ExcelUtils<>();
        ex.exportExcel("知乎根话题top5问题及答案", ExcelTitleConllection.ZHIHUTITLE, list, out);
        out.close();
    }

    /**
     * 执行sql存储
     *
     * @param conn  sqlconn
     * @param zhihu 知乎实体
     */
    private static void executeInsert(Connection conn, Zhihu zhihu) throws Exception {
        String insertSql = "insert into zhihu (title,content,concern,browsed,answer_count, answer1,like1,comment1, answer2,like2,comment2, answer3,like3,comment3, answer4,like4,comment4, answer5,like5,comment5, crawler_date) " +
                "values (?,?,?,?,?, ?,?,?, ?,?,?, ?,?,?, ?,?,?, ?,?,?, ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(insertSql);
        preparedStatement.setString(1, zhihu.getTitle());
        preparedStatement.setString(2, zhihu.getContent());
        preparedStatement.setString(3, zhihu.getConcern());
        preparedStatement.setString(4, zhihu.getBrowsed());
        preparedStatement.setString(5, zhihu.getAnswerCount());

        preparedStatement.setString(6, zhihu.getAnswer1());
        preparedStatement.setString(7, zhihu.getLike1());
        preparedStatement.setString(8, zhihu.getComment1());

        preparedStatement.setString(9, zhihu.getAnswer2());
        preparedStatement.setString(10, zhihu.getLike2());
        preparedStatement.setString(11, zhihu.getComment2());

        preparedStatement.setString(12, zhihu.getAnswer3());
        preparedStatement.setString(13, zhihu.getLike3());
        preparedStatement.setString(14, zhihu.getComment3());

        preparedStatement.setString(15, zhihu.getAnswer4());
        preparedStatement.setString(16, zhihu.getLike4());
        preparedStatement.setString(17, zhihu.getComment4());

        preparedStatement.setString(18, zhihu.getAnswer5());
        preparedStatement.setString(19, zhihu.getLike5());
        preparedStatement.setString(20, zhihu.getComment5());

        preparedStatement.setDate(21, new Date(System.currentTimeMillis()));
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }


    private static List<Zhihu> excueteQuery(Connection conn) throws Exception {
        List<Zhihu> list = new ArrayList<>();
        String insertSql = "select * from zhihu ";
        PreparedStatement preparedStatement = conn.prepareStatement(insertSql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String title = resultSet.getString(2);
            String content = resultSet.getString(3);
            String concern = resultSet.getString(4);
            String browsed = resultSet.getString(5);
            String answerCount = resultSet.getString(6);
            String answer1 = resultSet.getString(7);
            String like1 = resultSet.getString(8);
            String comment1 = resultSet.getString(9);
            Zhihu zhihu = new Zhihu(title, content, concern, browsed, answerCount, answer1, like1, comment1, "", "", "", "", "", "", "", "", "", "", "", "");
            list.add(zhihu);
        }
        return list;
    }

}
