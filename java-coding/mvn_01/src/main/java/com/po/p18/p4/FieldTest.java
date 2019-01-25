package com.po.p18.p4;

import java.lang.reflect.Field;

public class FieldTest {

	public static void main(String[] args) throws Exception {
		Person person = new Person();
		Class<Person> pClass = Person.class;
		Field fieldName = pClass.getDeclaredField("name");
		fieldName.setAccessible(true);
		fieldName.set(person, "feifei");
		Field fieldAge = pClass.getDeclaredField("age");
		fieldAge.setAccessible(true);
		fieldAge.set(person, 30);
		System.out.println(person);

	}

}

class Person {
	private String name;
	private int age;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Person[name = " + name + "£¬age = " + age + "]";
	}
}