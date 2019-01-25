package com.masterworker2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {
	private ConcurrentLinkedQueue<Task> workQuene = new ConcurrentLinkedQueue<>();
	private Map<String, Thread> workers = new HashMap<>();
	private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<>();

	public Master(Worker worker, int workerCount) {
		worker.setWorkQuene(this.workQuene);
		worker.setResultMap(this.resultMap);
		for (int i = 1; i <= workerCount; i++) {
			workers.put("子节点 - " + i, new Thread(worker));
		}
	}

	public void submit(Task task) {
		this.workQuene.add(task);
	}

	public void execute() {
		for (Map.Entry<String, Thread> me : workers.entrySet()) {
			me.getValue().start();
		}
	}

	public boolean isComplete() {
		for (Map.Entry<String, Thread> me : workers.entrySet()) {
			if (me.getValue().getState() != Thread.State.TERMINATED) {
				return false;
			}
		}
		return true;
	}

	public int getResult() {
		int ret = 0;
		for (Map.Entry<String, Object> me : resultMap.entrySet()) {
			ret += (int) me.getValue();
		}
		return ret;
	}
}