package com.masterworker2;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Worker implements Runnable {
	private ConcurrentLinkedQueue<Task> workQuene;
	private ConcurrentHashMap<String, Object> resultMap;

	public void setWorkQuene(ConcurrentLinkedQueue<Task> workQuene) {
		this.workQuene = workQuene;
	}

	public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	@Override
	public void run() {
		while (true) {
			Task input = this.workQuene.poll();
			if (input == null) {
				break;
			}
			Object output = handle(input);
			this.resultMap.put("worker - " + input.getId(), output);
		}
	}

	private Object handle(Task input) {
		Object output = null;
		try {
			Thread.sleep(500);
			output = input.getPrice();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
}
