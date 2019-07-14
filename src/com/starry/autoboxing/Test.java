package com.starry.autoboxing;

import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		//method1();
		method2();
	}
	
	public static void method2() {
		Integer a = 100;
		int _a = 100;
		Integer _a1 = new Integer(100);
		System.out.println("a == _a : " + (a == _a));     // true
		System.out.println("a == _a1 : " + (a == _a1));   // false
		System.out.println("_a == _a1 : " + (_a == _a1)); //true
		
		Integer b = 200;
		int _b = 100;
		Integer _b1 = new Integer(200);
		System.out.println("b == _b : " + (b == _b));     // false
		System.out.println("b == _b1 : " + (b == _b1));   // false
		System.out.println("_b == _b1 : " + (_b == _b1)); //false
		
		Integer result = 0;
		for (int i = 100; i < 1000; i++) {
			result += i;
		}
		
	}
	
	public static void method1() {
		ArrayList<Integer> intList = new ArrayList<Integer>();
		intList.add(100);
		intList.add(150);
		intList.add(200);
		
		int a = intList.get(0);
		int b = intList.get(1);
		int c = intList.get(2);
		
		System.out.println(a + ", " + b + ", " + c);
		
		intList.add(Integer.valueOf(100));
		intList.add(Integer.valueOf(150));
		intList.add(Integer.valueOf(200));
		
		int _a = intList.get(0).intValue();
		int _b = intList.get(1).intValue();
		int _c = intList.get(2).intValue();
		System.out.println(_a + ", " + _b + ", " + _c);
		
		System.out.println((_a == a) + ", " + (_b == b) + ", " + (_c == c));
	}
}
