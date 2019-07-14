package com.starry.innerclass;

public class Father {
	private String name ;
	private int age;
	public Father(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public static void doSome() {
		System.out.println("father static method");
	}
	static class Son{
		static {
			System.out.println("son static");
		}
		public void print() {
			System.out.println();
		}
		public static void method1() {
			
		}
	}
}
