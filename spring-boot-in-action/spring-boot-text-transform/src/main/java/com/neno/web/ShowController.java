package com.neno.web;

import com.neno.model.MyPoint;
import com.neno.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: root
 * @Date: 2019/1/22 9:34
 */
@Controller
public class ShowController {

    @Autowired
    private PointService pointService;


    @RequestMapping("/show")
    public String hello() {
        return "show";
    }

    @RequestMapping("/getpoints")
    @ResponseBody
    public Map<String, Object> getPoints() {
        Map<String, Object> modelMap = new HashMap<>(16);
        List<MyPoint> points = pointService.getPoints();
        modelMap.put("points", points);
        modelMap.put("rectangles", pointService.getRects());
        modelMap.put("success", true);
        return modelMap;
    }
}
