package com.neno.designpattern.behavior.mediator;

import java.util.HashMap;
import java.util.Map;

public interface Mediator {
	void register(String dname, Department d);

	void command(String dname);
}

class President implements Mediator {
	private Map<String, Department> map = new HashMap<>();

	@Override
	public void command(String name) {
		map.get(name).selfAction();
	}

	@Override
	public void register(String dname, Department d) {
		map.put(dname, d);
	}
}