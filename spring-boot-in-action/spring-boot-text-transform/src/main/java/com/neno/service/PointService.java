package com.neno.service;

import com.neno.model.MyPoint;
import com.neno.model.MyRect;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: root
 * @Date: 2019/1/22 10:55
 */
@Service
public class PointService {


    public List<MyPoint> getPoints() {
        List<MyPoint> points = Arrays.asList(new MyPoint(20, 20), new MyPoint(30, 30), new MyPoint(30, 300),
                new MyPoint(100, 350),
                new MyPoint(150, 450),
                new MyPoint(250, 450),
                new MyPoint(350, 450),
                new MyPoint(320, 50),
                new MyPoint(450, 20),
                new MyPoint(500, 20),
                new MyPoint(550, 60),
                new MyPoint(570, 480),
                new MyPoint(610, 480),
                new MyPoint(660, 460),
                new MyPoint(710, 460),
                new MyPoint(710, 300));
        return points;
    }

    public List<MyRect> getRects() {
        List<MyRect> rects = Arrays.asList(new MyRect(200, 50, 100, 300), new MyRect(400, 50, 100, 400), new MyRect(600, 50, 100, 400));
        return rects;
    }
}
