package com.clone.mvn_02.io.seriable;

import java.io.*;

public class SerializeDemo {
	public static void main(String[] args) {
		SerializeDemo sDemo = new SerializeDemo();
		sDemo.serializeDemo();
		System.out.println("======================================================");
		sDemo.deSerializeDemo();
	}

	/**
	 * 
	 * 序列化
	 */
	public void serializeDemo() {
		Employee e = new Employee();
		e.name = "Reyan Ali";
		e.address = "Phokka Kuan, Ambehta Peer";
		e.SSN = 11122333;
		e.number = 101;
		try {
			FileOutputStream fileOut = new FileOutputStream("D:/002---AllCode/eclipse/tmp/employee.txt");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(e);
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in D:/002---AllCode/eclipse/tmp/employee.txt");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	/**
	 * 
	 * 反序列化
	 */
	public void deSerializeDemo() {
		Employee e = null;
		try {
			FileInputStream fileIn = new FileInputStream("D:/002---AllCode/eclipse/tmp/employee.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e = (Employee) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Employee class not found");
			c.printStackTrace();
			return;
		}
		System.out.println("Deserialized Employee...");
		System.out.println("Name: " + e.name);
		System.out.println("Address: " + e.address);
		System.out.println("SSN: " + e.SSN);
		System.out.println("Number: " + e.number);
		System.out.println("num: " + Employee.num);
	}
}
