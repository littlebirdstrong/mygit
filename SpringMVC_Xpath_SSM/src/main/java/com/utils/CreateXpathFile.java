package com.utils;

import java.io.*;

/**
 * 生成Xpath文件
 * @path 文件路径
 * @filename 文件名称
 * @content 文件内容
 */
public class CreateXpathFile {
    public static void WriteXpath(String fileName,String content) throws FileNotFoundException, UnsupportedEncodingException {
        String path = ".\\Xpath\\";//文件路径
        File f = new File(path);
        if(!f.exists()){
            f.mkdirs();//创建目录
        }
        File file = new File(path,fileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BufferedWriter bufferedWriter;
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true),"utf-8"));

        try {
            bufferedWriter.write(content+"\r\n");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
