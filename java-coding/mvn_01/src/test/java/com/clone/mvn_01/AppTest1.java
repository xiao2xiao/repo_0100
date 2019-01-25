package com.clone.mvn_01;

import org.junit.Test;

import com.clone.mvn_01.model.m4.Address;
import com.clone.mvn_01.model.m4.Chinese;

public class AppTest1 {
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
		System.out.println("c1 = " + c1);// c1 = Chinese [name=Peter, age=31, address=Address [type=上海, value=japan]]
		System.out.println("c2 = " + c2);// c2 = Chinese [name=Jacky, age=31, address=Address [type=how are you,
											// value=japan]]
	}
}
