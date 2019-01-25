package com.clone.mvn_01.test;

import java.util.HashSet;
import java.util.Set;

public class HashTest {
	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * @Override public boolean equals(Object obj) { // TODO Auto-generated method
	 * stub if (obj == null) { return false; } if (obj == this) { return true; } if
	 * (obj instanceof HashTest) { HashTest other = (HashTest) obj; if
	 * (other.getId() == this.getId() && other.getName().equals(this.getName())) {
	 * return true; } else { return false; } } else { return false; } }
	 * 
	 * @Override public int hashCode() { // TODO Auto-generated method stub return
	 * id % 10; }
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HashTest other = (HashTest) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public static void main(String[] args) {
		HashTest aHashTest = new HashTest();
		HashTest bHashTest = new HashTest();
		aHashTest.setId(1);
		aHashTest.setName("tom");
		bHashTest.setId(1);
		bHashTest.setName("tom");
		Set<HashTest> sets = new HashSet<>();
		sets.add(aHashTest);
		sets.add(bHashTest);
		System.out.println(aHashTest.hashCode() == bHashTest.hashCode());
		System.out.println(aHashTest.equals(bHashTest));
		System.out.println(sets);
	}
}
