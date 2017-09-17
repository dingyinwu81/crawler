package com.dyw.crawler.model;

import java.sql.Date;

/**
 * 知乎实体
 * Created by dyw on 2017/9/17.
 */
public class Zhihu {
    //标题
    private String title;
    //内容
    private String content;
    //关注者
    private String concern;
    //浏览数
    private String browsed;
    //爬取时答案数
    private String answerCount;
    //回答1
    private String answer1;
    //点赞数1
    private String like1;
    //评论数1
    private String comment1;

    private String answer2;
    private String like2;
    private String comment2;

    private String answer3;
    private String like3;
    private String comment3;

    private String answer4;
    private String like4;
    private String comment4;

    private String answer5;
    private String like5;
    private String comment5;

    private Date crawler_date;

    public String getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(String answerCount) {
        this.answerCount = answerCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getConcern() {
        return concern;
    }

    public void setConcern(String concern) {
        this.concern = concern;
    }

    public String getBrowsed() {
        return browsed;
    }

    public void setBrowsed(String browsed) {
        this.browsed = browsed;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getLike1() {
        return like1;
    }

    public void setLike1(String like1) {
        this.like1 = like1;
    }

    public String getComment1() {
        return comment1;
    }

    public void setComment1(String comment1) {
        this.comment1 = comment1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getLike2() {
        return like2;
    }

    public void setLike2(String like2) {
        this.like2 = like2;
    }

    public String getComment2() {
        return comment2;
    }

    public void setComment2(String comment2) {
        this.comment2 = comment2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getLike3() {
        return like3;
    }

    public void setLike3(String like3) {
        this.like3 = like3;
    }

    public String getComment3() {
        return comment3;
    }

    public void setComment3(String comment3) {
        this.comment3 = comment3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getLike4() {
        return like4;
    }

    public void setLike4(String like4) {
        this.like4 = like4;
    }

    public String getComment4() {
        return comment4;
    }

    public void setComment4(String comment4) {
        this.comment4 = comment4;
    }

    public String getAnswer5() {
        return answer5;
    }

    public void setAnswer5(String answer5) {
        this.answer5 = answer5;
    }

    public String getLike5() {
        return like5;
    }

    public void setLike5(String like5) {
        this.like5 = like5;
    }

    public String getComment5() {
        return comment5;
    }

    public void setComment5(String comment5) {
        this.comment5 = comment5;
    }

    public Date getCrawler_date() {
        return crawler_date;
    }

    public void setCrawler_date(Date crawler_date) {
        this.crawler_date = crawler_date;
    }

    public Zhihu(){

    }

    //构造函数
    public Zhihu(String title, String content, String concern, String browsed,String answerCount,String answer1, String like1, String comment1, String answer2, String like2, String comment2, String answer3, String like3, String comment3, String answer4, String like4, String comment4, String answer5, String like5, String comment5) {
        this.title = title;
        this.content = content;
        this.concern = concern;
        this.browsed = browsed;
        this.answerCount = answerCount;
        this.answer1 = answer1;
        this.like1 = like1;
        this.comment1 = comment1;
        this.answer2 = answer2;
        this.like2 = like2;
        this.comment2 = comment2;
        this.answer3 = answer3;
        this.like3 = like3;
        this.comment3 = comment3;
        this.answer4 = answer4;
        this.like4 = like4;
        this.comment4 = comment4;
        this.answer5 = answer5;
        this.like5 = like5;
        this.comment5 = comment5;
    }
}
