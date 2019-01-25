package com.clone.mvn_01;

import org.junit.Ignore;
import org.junit.Test;

import com.clone.mvn_01.model.m1.Man;
import com.clone.mvn_01.model.m2.Person;
import com.clone.mvn_01.model.m3.Address;
import com.clone.mvn_01.model.m3.Chinese;

public class AppTest {
	@Test
	@Ignore
	public void testassign() {
		Man m1 = new Man();
		m1.setAge(31);
		m1.setName("Peter");
		System.out.println(m1.toString());

		Man m2 = m1;
		System.out.println(m2.toString());
		System.out.println(m1 == m2);// true
	}

	@Test
	@Ignore
	public void testShallowCopy() throws CloneNotSupportedException {
		Person p1 = new Person();
		p1.setAge(31);
		p1.setName("Peter");

		Person p2 = (Person) p1.clone();
		System.out.println(p1 == p2);// false
		p2.setName("Jacky");
		System.out.println("p1 = " + p1);// p1=Person [name=Peter, age=31]
		System.out.println("p2 = " + p2);// p2=Person [name=Jacky, age=31]
	}

	@Test
	public void testShallowCopy1() throws CloneNotSupportedException {
		Address address = new Address();
		address.setType("上海");
		address.setValue("japan");
		Chinese c1 = new Chinese();
		c1.setAge(31);
		c1.setName("Peter");
		c1.setAddress(address);

		Chinese c2 = (Chinese) c1.clone();
		System.out.println(c1 == c2);// false
		c2.setName("Jacky");
		c2.getAddress().setType("how are you");
		System.out.println("c1 = " + c1);// c1 = Chinese [name=Peter, age=31, address=Address [type=how are you,
											// value=japan]]
		System.out.println("c2 = " + c2);// c2 = Chinese [name=Jacky, age=31, address=Address [type=how are you,
											// value=japan]]]
	}
}
