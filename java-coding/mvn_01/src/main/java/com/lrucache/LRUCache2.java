package com.lrucache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache2<K, V> extends LinkedHashMap<K, V> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private final int CACHE_SIZE;

	// 这里就是传递进来最多能缓存多少数据
	public LRUCache2(int cacheSize) {
		/**
		 * 这块就是设置一个hashmap的初始大小，同时最后一个true指的是让linkedhashmap按照访问顺序来进行排序，最近访问的放在头，最老访问的就在尾
		 * 
		 */
		super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
		this.CACHE_SIZE = cacheSize;
	}

	@Override
	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		return false;
	}
}
