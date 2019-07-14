package com.starry.stringtoInteger;

public class Test {
	public static void main(String[] args) {
		method();
	}
	
	public static void method() {
		String str = "-255";
		Integer num = Integer.parseInt(str);
		System.out.println(num);
	}
}
