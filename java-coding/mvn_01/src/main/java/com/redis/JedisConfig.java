package com.redis;

import java.util.List;

import redis.clients.jedis.Jedis;

public class JedisConfig {
	private static final String ADDRESS = "192.168.254.134";
	private static final int PORT = 6379;
	private static Jedis jedis = null;
	static {
		jedis = new Jedis(ADDRESS, PORT);
	}

	public static void main(String[] args) {
		// jedis.set("name", "zhangsan");
		// System.out.println(jedis.get("name"));
		jedis.expire("name", 10000);
		System.out.println("-----------------");
		jedis.lpush("site-list", "Runoob");
		jedis.lpush("site-list", "Google");
		jedis.lpush("site-list", "Taobao");
		List<String> lists = jedis.lrange("site-list", 0, 2);
		for (int i = 0; i < lists.size(); i++) {
			System.out.println(lists.get(i));
		}
		jedis.close();
	}
}
