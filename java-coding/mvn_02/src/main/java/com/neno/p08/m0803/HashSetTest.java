package com.neno.p08.m0803;

import java.util.HashSet;

//类A的equals方法总是返回true，但没有重写其hashCode()方法
class A {
	public boolean equals(Object obj) {
		return true;
	}
}

// 类B的hashCode()方法总是返回1，但没有重写其equals()方法
class B {
	public int hashCode() {
		return 1;
	}
}

// 类C的hashCode()方法总是返回2，且重写其equals()方法总是返回true
class C {
	public int hashCode() {
		return 2;
	}

	public boolean equals(Object obj) {
		return true;
	}
}

public class HashSetTest {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		HashSet books = new HashSet<>();
		books.add(new A());
		books.add(new A());
		books.add(new B());
		books.add(new B());
		books.add(new C());
		books.add(new C());

		System.out.println(books);
	}
}
