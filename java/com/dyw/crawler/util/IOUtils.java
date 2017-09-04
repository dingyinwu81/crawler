package com.dyw.crawler.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
        writeFile(content.getBytes("Utf-8"), fileName);
    }

    /**
     * 写入bytes到file中
     *
     * @param bytes    写入内容
     * @param fileName 写入位置
     */
    public static void writeFile(byte[] bytes, File fileName) throws Exception {
        FileOutputStream o;
        try {
            o = new FileOutputStream(fileName);
            o.write(bytes);
            o.close();
        } catch (Exception e) {
            throw new Exception("写入文件的时候错误！", e);
        }
    }

    /**
     * 保存inputStream到文件
     *
     * @param inputStream 输入流
     * @param fileName    保存文件的位置
     */
    public static void saveFile(InputStream inputStream, File fileName) throws Exception {
        writeFile(readInputStream(inputStream), fileName);
    }

    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream 输入流
     * @return byte数组
     */
    private static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        inputStream.close();
        return bos.toByteArray();
    }
}
