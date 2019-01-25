package com.masterworker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Worker implements Runnable {

	private ConcurrentLinkedQueue<Task> workerQuene;
	private ConcurrentHashMap<String, Object> resultMap;

	public void setWorkerQuene(ConcurrentLinkedQueue<Task> workerQuene) {
		this.workerQuene = workerQuene;
	}

	public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	@Override
	public void run() {
		while (true) {
			/**
			 * 取出该task
			 */
			Task input = this.workerQuene.poll();
			if (input == null) {
				break;
			}
			/**
			 * 真正去做业务处理
			 */

			Object output = handle(input);
			/**
			 * 把处理完成的结果集放入到map里面
			 */
			this.resultMap.put(String.valueOf(input.getId()), output);
		}
	}

	public Object handle(Task input) {
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
