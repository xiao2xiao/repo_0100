package com.po.jvm.p02;

import java.util.ArrayList;
import java.util.List;

public class RuntimeConstantPoolOOM {
	public static void main(String[] args) {
		List<String> lists = new ArrayList<>();
		int i = 0;
		while (true) {
			lists.add(String.valueOf(i++).intern());
		}
	}
}
