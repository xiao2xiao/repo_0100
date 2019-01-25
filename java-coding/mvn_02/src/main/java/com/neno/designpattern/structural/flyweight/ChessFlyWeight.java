package com.neno.designpattern.structural.flyweight;

/**
 * 通常是一个接口或抽象类，声明公共方法，这些方法可以向外界提供对象 的内部状态，设置外部状态
 * 
 * @author root
 *
 */
public interface ChessFlyWeight {
	void setColor(String c);

	String getColor();

	void display(Coordinate c);
}

/**
 * 为内部状态提供成员变量进行存储
 * 
 * @author root
 *
 */
class ConcreteChessFlyWeight implements ChessFlyWeight {

	private String color;

	public ConcreteChessFlyWeight(String color) {
		super();
		this.color = color;
	}

	@Override
	public void display(Coordinate c) {
		System.out.println("棋子颜色：" + color);
		System.out.println("棋子位置：" + c.getX() + "----" + c.getY());
	}

	@Override
	public String getColor() {
		return color;
	}

	@Override
	public void setColor(String c) {
		this.color = c;
	}

}