package com.neno.designpattern.behavior.state;

/**
 * 
 * @author root 该类代表当前的状态以及状态切换的核心方法
 */
public class Context {
	private State state;

	public Context() {
		// TODO Auto-generated constructor stub
	}

	public Context(State state) {
		this.state = state;
	}

	public void setState(State state) {
		System.out.println("修改状态。。。。");
		this.state = state;
		state.handle();
	}

	public State getState() {
		return state;
	}
}
