package com.starry.annotation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Date.parse("");
		
		@SuppressWarnings({"unused" })
		List<Integer> list = new ArrayList<Integer>();
	}
}
class A {
	void method1() {
		System.out.println("a");
	}
	
	
}
class B extends A {
	@Override
	void method1() {
		System.out.println("b");
	}
}