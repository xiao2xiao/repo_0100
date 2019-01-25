package com.neno.designpattern.behavior.memento;

public class Client {
	public static void main(String[] args) {
		CareTaker taker = new CareTaker();

		EmpOriginator emp = new EmpOriginator("高淇", 18, 900);
		System.out.println("第一次打印对象：" + emp.getEname() + "---" + emp.getAge() + "---" + emp.getSalary());

		taker.setEmpMemento(emp.createMemento()); // 备忘一次

		emp.setAge(38);
		emp.setEname("搞起");
		emp.setSalary(9000);
		System.out.println("第二次打印对象：" + emp.getEname() + "---" + emp.getAge() + "---" + emp.getSalary());

		emp.recovery(taker.getEmpMemento()); // 恢复到备忘录对象保存的状态

		System.out.println("第三次打印对象：" + emp.getEname() + "---" + emp.getAge() + "---" + emp.getSalary());
	}
}
