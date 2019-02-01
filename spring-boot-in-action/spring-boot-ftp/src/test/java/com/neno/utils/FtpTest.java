package com.neno.utils;

import com.neno.FtpApplicationTests;
import com.neno.config.FtpConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @Author: root
 * @Date: 2019/2/1 19:22
 */
public class FtpTest extends FtpApplicationTests {

    @Autowired
    private FtpConfig ftpConfig;

    @Test
    public void testUploadFile() {

        File file = new File("D:\\opt\\app\\nginx\\html\\pic\\1500795697", "floor-plan-01.jpg");
        String oldName = file.getName();
        String picNewName = UploadUtils.generateRandonFileName(oldName);//通过工具类产生新图片名称，防止重名
        String picSavePath = UploadUtils.generateRandomDir(picNewName);//通过工具类把图片目录分级
        //设置图片的url--》就是存储到数据库的字符串url
        System.out.println(picSavePath + "/" + picNewName);
        try {
            FtpFileUtil.pictureUploadByConfig(ftpConfig, picNewName, picSavePath, new FileInputStream(file));//上传到图片服务器的操作
            System.out.println("上传成功");
        } catch (IOException e) {
            e.printStackTrace();
        }


        //上传成功做的操作,我这里返回上传成功的信号
    }
}
