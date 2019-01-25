package com.masterworker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {
	/**
	 * 1.承装所有的任务的集合
	 */
	private ConcurrentLinkedQueue<Task> workerQuene = new ConcurrentLinkedQueue<>();
	/**
	 * 2.承装所有的wokers
	 */
	private Map<String, Thread> workers = new HashMap<>();
	/**
	 * 3.存储所有任务的结果集合
	 */
	private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<>();

	/**
	 * 4.构造方法，管理所有的worker，把每一个worker添加到hashmap里面
	 * 
	 * @param worker
	 * @param workCount
	 */
	public Master(Worker worker, int workCount) {
		/**
		 * 每一个worker对象都需要有master的引用，第一个用于worker的领取，第二个用于结果的提交
		 */
		worker.setWorkerQuene(this.workerQuene);
		worker.setResultMap(this.resultMap);
		for (int i = 0; i < workCount; i++) {
			workers.put("工作线程-" + String.valueOf(i), new Thread(worker));
		}

	}

	/**
	 * 5.提交方法
	 * 
	 * @param task
	 */
	public void submit(Task task) {
		this.workerQuene.add(task);
	}

	/**
	 * 6.执行的方法(让所有的worker工作)
	 */
	public void execute() {
		for (Map.Entry<String, Thread> me : workers.entrySet()) {
			me.getValue().start();
		}
	}

	/**
	 * 判断是否结束运行
	 * 
	 * @return
	 */
	public boolean isComplete() {
		for (Map.Entry<String, Thread> me : workers.entrySet()) {
			if (me.getValue().getState() != Thread.State.TERMINATED) {
				return false;
			}
		}
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * 返回结果集
	 * 
	 * @return
	 */
	public int getResult() {
		int ret = 0;
		for (Map.Entry<String, Object> me : resultMap.entrySet()) {
			ret += (Integer) me.getValue();
		}
		return ret;
	}
}
