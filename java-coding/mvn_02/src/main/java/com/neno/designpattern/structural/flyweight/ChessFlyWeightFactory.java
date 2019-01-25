package com.neno.designpattern.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建并管理享元对象，享元池一般设计成键值对
 * 
 * @author root
 *
 */
public class ChessFlyWeightFactory {
	private static Map<String, ChessFlyWeight> chess = new HashMap<>();

	public static ChessFlyWeight getChess(String c) {
		if (chess.get(c) != null) {
			return chess.get(c);
		} else {
			ChessFlyWeight cfw = new ConcreteChessFlyWeight("黑色");
			chess.put(c, cfw);
			return cfw;
		}
	}
}
