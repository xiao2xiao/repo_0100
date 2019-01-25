package com.neno.p06.m0608;

public class LambdaQs {
	// 调用该方法需要Eatable对象
	public void eat(Eatable e) {
		System.out.println(e);
		e.taste();
	}

	// 调用该方法需要Flyable对象
	public void drive(Flyable f) {
		System.out.println("我正在驾驶：" + f);
		f.fly("【碧空如洗的晴日】");
	}

	// 调用该方法需要Addable对象
	public void test(Addable add) {
		System.out.println("5与3的和为：" + add.add(5, 3));
	}

	public static void main(String[] args) {
		LambdaQs lambdaQs = new LambdaQs();
		lambdaQs.eat(() -> System.out.println("very good"));
		lambdaQs.drive(weather -> {
			System.out.println("今天天气是：" + weather);
			System.out.println("how are you");
		});
		lambdaQs.test((a, b) -> a + b);
	}
}

interface Eatable {
	void taste();
}

interface Flyable {
	void fly(String weather);
}

interface Addable {
	int add(int a, int b);
}