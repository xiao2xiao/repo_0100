package com.clone.mvn_01.model.m5;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DeepCloneTest {
	public static void main(String[] args) throws Exception {
		// teacher对象将不被clone出来的Student对象共享.
		Teacher teacher = new Teacher();
		teacher.setAge(40);
		teacher.setName("Teacher zhang");

		Student student1 = new Student();
		student1.setAge(20);
		student1.setName("zhangsan");
		student1.setTeacher(teacher);

		// 复制出来一个对象student2
		Student student2 = (Student) student1.deepCopy();
		System.out.println(student2.getAge());
		System.out.println(student2.getName());

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(student1.getTeacher().getAge());
		System.out.println(student1.getTeacher().getName());

		// 修改student2的引用对象
		student2.getTeacher().setAge(50);
		student2.getTeacher().setName("Teacher Li");

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(student1.getTeacher().getAge());
		System.out.println(student1.getTeacher().getName());
	}
}

class Teacher implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7806051773227780325L;
	public int age;
	public String name;

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

}

class Student implements Serializable {

	/**
	 * 
	 */

	// serialVersionUID
	// 如果你的对象序列化后存到硬盘上面后，可是后来你却更改了类的field(增加或减少或改名)，当你反序列化时，就会出现Exception的，这样就会造成不兼容性的问题。
	// 但当serialVersionUID相同时，它就会将不一样的field以type的缺省值赋值(如int型的是0,String型的是null等)，这个可以避开不兼容性的问题。所以最好给serialVersionUID赋值
	private static final long serialVersionUID = 3173944462479242204L;
	public int age;
	public String name;
	public Teacher teacher;

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

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Object deepCopy() throws Exception {
		// 将该对象序列化成流,因为写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面。所以利用这个特性可以实现对象的深拷贝
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		ObjectOutputStream oos = new ObjectOutputStream(bos);

		oos.writeObject(this);

		// 将流序列化成对象
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());

		ObjectInputStream ois = new ObjectInputStream(bis);

		return ois.readObject();
	}

}
