package com.neno.controller;

import com.neno.model.BusVod;
import com.neno.service.BusVodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: root
 * @Date: 2019/4/26 17:28
 */
@Controller
public class BusVodController {

    @Autowired
    private BusVodService busVodService;

    /**
     * 实现全文检索功能
     *
     * @param keyword
     * @param map
     * @return
     */
    @RequestMapping(value = "/moviesList", method = RequestMethod.GET)
    public String query(@RequestParam(name = "keyword", required = false) String keyword,
                        ModelMap map) {
        List<BusVod> busVodList = busVodService.searchEntity();
        map.addAttribute("movieLists", busVodList);
        return "moviesList";
    }
}
