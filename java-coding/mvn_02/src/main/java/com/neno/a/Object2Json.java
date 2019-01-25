package com.neno.a;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Object2Json {
	public static class Person {
		private int age;
		private String name;
		private int id;

		public Person(int age, String name, int id) {
			super();
			this.age = age;
			this.name = name;
			this.id = id;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

	}

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		Person person = new Person(10, "aaa", 5);
		Class<?> p = person.getClass();
		Map<String, Object> map = new HashMap<>();
		Field[] fields = p.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			map.put(field.getName(), field.get(person));
		}
		for (Map.Entry<String, Object> f : map.entrySet()) {
			System.out.println("key = " + f.getKey() + " , value = " + f.getValue());
		}
	}
}
