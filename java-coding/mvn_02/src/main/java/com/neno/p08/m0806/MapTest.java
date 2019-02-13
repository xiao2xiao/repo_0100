package com.neno.p08.m0806;

import java.util.*;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class MapTest {
	public static void main(String[] args) {
		fun5();
	}

	/**
	 * EnumMapTest
	 */
	enum Season {
		SPRING, SUMMER, FALL, WINTER
	}

	static void fun5() {
		EnumMap<Season, Object> enumMap = new EnumMap<>(Season.class);
		enumMap.put(Season.SPRING, "春天好。。。");
		enumMap.put(Season.SUMMER, "夏天热。。。");
		System.out.println(enumMap);
	}

	/**
	 * IdentityHashMapTest
	 */
	static void fun4() {
		IdentityHashMap<String, Object> identityHashMap = new IdentityHashMap<>();
		identityHashMap.put(new String("语文"), 88);
		identityHashMap.put(new String("语文"), 90);
		identityHashMap.put("语文", 80);
		identityHashMap.put("语文", 70);
		System.out.println(identityHashMap);
	}

	/**
	 * TreeMapTest
	 */
	static class R implements Comparable {
		int count;

		public R(int count) {
			this.count = count;
		}

		public String toString() {
			return "R[count:" + count + "]";
		}

		// 根据count来判断两个对象是否相等。
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj != null && obj.getClass() == R.class) {
				R r = (R) obj;
				return r.count == this.count;
			}
			return false;
		}

		// 根据count属性值来判断两个对象的大小。
		public int compareTo(Object obj) {
			R r = (R) obj;
			return count > r.count ? 1 : count < r.count ? -1 : 0;
		}
	}

	static void fun3() {
		TreeMap tm = new TreeMap();
		tm.put(new R(3), "轻量级Java EE企业应用实战");
		tm.put(new R(-5), "疯狂Java讲义");
		tm.put(new R(9), "疯狂Android讲义");
		System.out.println(tm);
		// 返回该TreeMap的第一个Entry对象
		System.out.println(tm.firstEntry());
		// 返回该TreeMap的最后一个key值
		System.out.println(tm.lastKey());
		// 返回该TreeMap的比new R(2)大的最小key值。
		System.out.println(tm.higherKey(new R(2)));
		// 返回该TreeMap的比new R(2)小的最大的key-value对。
		System.out.println(tm.lowerEntry(new R(2)));
		// 返回该TreeMap的子TreeMap
		System.out.println(tm.subMap(new R(-1), new R(4)));
	}

	/**
	 * LinkedHashMapTest
	 */
	static void fun2() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<>();
		// 成对放入多个key-value对
		map.put("疯狂Java讲义", 109);
		map.put("疯狂iOS讲义", 10);
		map.put("疯狂Ajax讲义", 79);
		// 多次放入的key-value对中value可以重复
		map.put("轻量级Java EE企业应用实战", 99);
		map.forEach((key, value) -> System.out.println("key = " + key + " ,value = " + value));
	}

	/**
	 * MapTest
	 */
	static void fun() {
		Map map = new HashMap();
		// 成对放入多个key-value对
		map.put("疯狂Java讲义", 109);
		map.put("疯狂iOS讲义", 10);
		map.put("疯狂Ajax讲义", 79);
		// 多次放入的key-value对中value可以重复
		map.put("轻量级Java EE企业应用实战", 99);
		// 放入重复的key时，新的value会覆盖原有的value
		// 如果新的value覆盖了原有的value，该方法返回被覆盖的value
		System.out.println(map.put("疯狂iOS讲义", 99)); // 输出10
		System.out.println(map); // 输出的Map集合包含4个key-value对
		// 判断是否包含指定key
		System.out.println("是否包含值为 疯狂iOS讲义 key：" + map.containsKey("疯狂iOS讲义")); // 输出true
		// 判断是否包含指定value
		System.out.println("是否包含值为 99 value：" + map.containsValue(99)); // 输出true
		// 获取Map集合的所有key组成的集合，通过遍历key来实现遍历所有key-value对
		for (Object key : map.keySet()) {
			// map.get(key)方法获取指定key对应的value
			System.out.println(key + "-->" + map.get(key));
		}
		map.remove("疯狂Ajax讲义"); // 根据key来删除key-value对。
		System.out.println(map); // 输出结果不再包含 疯狂Ajax讲义=79 的key-value对
	}
}
