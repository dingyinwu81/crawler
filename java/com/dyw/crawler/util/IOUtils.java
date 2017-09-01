package com.dyw.crawler.util;

import java.io.File;
import java.io.FileOutputStream;

/**
 * IO工具类
 * Created by dyw on 2017/9/1.
 */
public class IOUtils {

    /**
     * 创建文件
     *
     * @param file File类型
     */
    public static void createFile(File file) throws Exception {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            throw new Exception("创建文件的时候错误！", e);
        }
    }

    /**
     * 写入String到file中
     *
     * @param content  写入内容
     * @param fileName 写入位置
     */
    public static void writeFile(String content, File fileName) throws Exception {
        FileOutputStream o;
        try {
            o = new FileOutputStream(fileName);
            o.write(content.getBytes("Utf-8"));
            o.close();
        } catch (Exception e) {
            throw new Exception("写入文件的时候错误！", e);
        }
    }
}
