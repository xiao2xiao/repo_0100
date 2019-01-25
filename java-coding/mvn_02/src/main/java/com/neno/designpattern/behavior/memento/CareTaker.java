package com.neno.designpattern.behavior.memento;

/**
 * 负责人类-负责管理备忘录对象
 * 
 * @author root
 *
 */
public class CareTaker {
	private EmpMemento empMemento;

	public EmpMemento getEmpMemento() {
		return empMemento;
	}

	public void setEmpMemento(EmpMemento empMemento) {
		this.empMemento = empMemento;
	}

}
