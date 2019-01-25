package com.guava.joiner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Joiner;

public class TestJoiner {
	public static void main(String[] args) {
		Map<String, String> testMap = new HashMap<>();
		testMap.put("Washington D.C", "Redskins");
		testMap.put("New York City", "Giants");
		testMap.put("Philadelphia", "Eagles");
		testMap.put("Dallas", "Cowboys");
		String string = Joiner.on("#").withKeyValueSeparator("=").join(testMap);
		System.out.println(string.toString());
		System.out.println("===========================");
		List<String> lists = new ArrayList<>();
		lists.add("Washington D.C");
		lists.add("New York City");
		lists.add("Philadelphia");
		lists.add("Dallas");
		string = Joiner.on("&").join(lists);
		System.out.println(string.toString());
	}
}
