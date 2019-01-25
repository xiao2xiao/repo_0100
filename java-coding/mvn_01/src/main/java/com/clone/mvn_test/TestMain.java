package com.clone.mvn_test;

import java.util.Random;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new Random(10).nextInt());

		Random random = new Random();
		int x = random.nextInt(100) + 100;
		System.out.println(x);
	}

}
