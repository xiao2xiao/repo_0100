package com.clone.mvn_01.test1;

public class TestMain {
	public static void main(String[] args) {
		MaA a = new MaA();
		Mnea b = new Mnea();
		System.out.println(a instanceof Mnea);
		System.out.println(a instanceof MaA);
		System.out.println("---------------------------");
		System.out.println(b instanceof Mnea);
		System.out.println(b instanceof MaA);
		System.out.println("---------------------------");
		System.out.println(fun(5));
	}

	@SuppressWarnings("finally")
	public static int fun(int i) {
		try {
			return i;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			System.out.println("how..............");
			return 0;
		}

	}
}
