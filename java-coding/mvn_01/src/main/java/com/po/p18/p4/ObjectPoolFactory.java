package com.po.p18.p4;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ObjectPoolFactory {
	private static Map<String, Object> objectPool = new HashMap<>();

	@SuppressWarnings("unused")
	private Object createObject(String clazzName) throws Exception {
		Class<?> clazz = Class.forName(clazzName);
		return clazz.newInstance();
	}

	public void initPool(String fileName) {

		try (FileInputStream fis = new FileInputStream(fileName)) {
			Properties props = new Properties();
			props.load(fis);
			for (String name : props.stringPropertyNames()) {
				objectPool.put(name, createObject(props.getProperty(name)));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Object getObject(String name) {
		return objectPool.get(name);
	}

	public static void main(String[] args) {
		ObjectPoolFactory opf = new ObjectPoolFactory();
		opf.initPool("obj.txt");
		for (Map.Entry<String, Object> entry : objectPool.entrySet()) {
			System.out.println("key = " + entry.getKey() + " £¬ value = " + entry.getValue());
		}
		System.out.println(opf.getObject("a"));
	}
}
