package com.po.jvm.p02;

public class JavaVMStackSOF {
	private static int stackLength = 1;

	public void stackLeap() {
		stackLength++;
		stackLeap();
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		JavaVMStackSOF jSof = new JavaVMStackSOF();
		try {
			jSof.stackLeap();
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println("stack length:" + jSof.stackLength);
			throw e;
		}
	}
}
