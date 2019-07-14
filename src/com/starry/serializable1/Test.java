package com.starry.serializable1;

public class Test {
	static int a = 0;
	
	private String name;
	
	public Test(String name) {
		this.name = name;
	}
	
	public static void main(String[] args) {
		System.out.println(a);
	}
	
	public static void method1() {
		System.out.println(a);
		int a;
	}
}
