package com.neno.service;

import com.neno.model.MyPoint;
import com.neno.model.MyRect;
import com.sun.org.apache.bcel.internal.util.ClassPath;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: root
 * @Date: 2019/1/22 10:55
 */
@Service
public class PointService {

    private static String path;

    static {
        try {
            path = ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<MyPoint> getPoints() {
        List<MyPoint> points = getPointsFromFile(path + "txt/point.txt");
        return points;
    }

    public List<MyRect> getRects() {
        List<MyRect> rects = getMyRectsFromFile(path + "txt/rectArea.txt");
        return rects;
    }

    /**
     * 获取myrect列表
     *
     * @param filePath
     * @return
     */
    private List<MyRect> getMyRectsFromFile(String filePath) {
        List<MyRect> rects = new ArrayList<>();
        List<String> lists = readTxtFileIntoStringArrList(filePath);
        String regex = ",|;";
        for (String str : lists) {
            if (str != "") {
                String[] s = str.split(regex);
                /**
                 * 坐标转化
                 */
                int x = Integer.parseInt(s[0]);
                int y = Integer.parseInt(s[1]);
                int width = Integer.parseInt(s[2]) - x;
                int height = Integer.parseInt(s[3]) - y;
                rects.add(new MyRect(x, y, width, height));
            }
        }
        return rects;
    }

    /**
     * 获取mypoint列表
     *
     * @param filePath
     * @return
     */
    private List<MyPoint> getPointsFromFile(String filePath) {
        List<MyPoint> points = new ArrayList<>();
        List<String> lists = readTxtFileIntoStringArrList(filePath);
        for (String str : lists) {
            if (str.contains(",")) {
                String[] s = str.split(",");
                MyPoint myPoint = new MyPoint(Double.parseDouble(s[0]), Double.parseDouble(s[1]));
                points.add(myPoint);
            }
        }
        return points;
    }

    /**
     * 功能：Java读取txt文件的内容 步骤：1：先获得文件句柄 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * 3：读取到输入流后，需要读取生成字节流 4：一行一行的输出。readline()。 备注：需要考虑的是异常情况
     *
     * @param filePath 文件路径[到达文件]
     * @return 将这个文件按照每一行切割成数组存放到list中。
     */
    private List<String> readTxtFileIntoStringArrList(String filePath) {
        List<String> list = new ArrayList<>();
        try {
            String encoding = "UTF-8";
            File file = new File(filePath);
            /**
             * 判断文件是否存在
             */
            if (file.isFile() && file.exists()) {
                /**
                 * 考虑到编码格式
                 */
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                while ((lineTxt = bufferedReader.readLine()) != null) {
                    list.add(lineTxt);
                }
                bufferedReader.close();
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

        return list;
    }
}