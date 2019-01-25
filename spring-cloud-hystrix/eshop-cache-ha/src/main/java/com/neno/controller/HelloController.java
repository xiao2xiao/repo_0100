package com.neno.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	@RequestMapping("/hello")
	@ResponseBody
	public String hello(String name) {
		return "hello" + name;
	}

	@RequestMapping("/hello2")
	@ResponseBody
	public String hello2(@RequestParam("pids") String pids) {
		String[] strings = pids.split(",");
		for (String string : strings) {
			System.out.println("--->" + string);
		}
		System.out.println("+++++++++++++++++++++++++++++++");
		Long[] pid = new Long[strings.length];
		for (int i = 0; i < strings.length; i++) {
			pid[i] = Long.valueOf(strings[i]);
		}
		for (Long p : pid) {
			System.out.println("--->" + p);
		}
		return "hello" + pids;
	}

}
