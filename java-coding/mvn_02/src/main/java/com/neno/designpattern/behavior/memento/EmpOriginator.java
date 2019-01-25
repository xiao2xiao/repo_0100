package com.neno.designpattern.behavior.memento;

/**
 * 源发器类
 * 
 * @author root
 *
 */
public class EmpOriginator {
	private String ename;
	private int age;
	private double salary;

	public EmpOriginator(String ename, int age, double salary) {
		super();
		this.ename = ename;
		this.age = age;
		this.salary = salary;
	}

	/**
	 * 进行备忘
	 * 
	 * @return
	 */
	public EmpMemento createMemento() {
		return new EmpMemento(this);
	}

	/**
	 * 进行恢复,恢复到该备忘点
	 * 
	 * @return
	 */
	public void recovery(EmpMemento e) {
		this.ename = e.getEname();
		this.age = e.getAge();
		this.salary = e.getSalary();
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

}
