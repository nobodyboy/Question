package com.starry.static1;

public class StaticTest {
	public static void main(String[] args) {
		for (String arg : args) {
			System.out.println(arg);
		}
	}
}
class A {
	static {
		System.out.println("A 静态代码块");
	}
	
	{
		System.out.println("A 实例代码块");
	}
	
	public A() {
		System.out.println("A 构造方法");
	}
}
class B extends A {
	static {
		System.out.println("B 静态代码块");
	}
	
	{
		System.out.println("B 实例代码块");
	}
	
	public B() {
		System.out.println("B 构造方法");
	}
}

class C {
	static {
		System.out.println("c : 静态代码块");
	}
	
	static int i ;  // 静态变量
	
	static void method() {
		System.out.println("c : 静态方法");
	}
}

class D {
	static {
		i = 2;
		System.out.println("D : 静态代码块");
	}
	static final int i;
	int j;
	static D d = new D();
	static void method() {
		System.out.println(i);
		System.out.println(new D().j);
		
		method1();
		new D().method2();
		d.method2();
	}
	
	static void method1() {
		System.out.println(i);
	}
	void method2() {
		System.out.println(i);
	}
}

class E {
	static int i;
	int j;
	
	void method() {
		System.out.println(i + " , " + j);
	}
	static void method1() {
		
		System.out.println(i + " , ");
	}
	
	static class InnerClass {
		static int _i;
		int _j;
		
		void metod() {
			System.out.println(_i + " , " + _j);
			//System.out.println(i + " , " + j);
		}
		
		static void method1() {
			//System.out.println(i + " , " + j);
			//System.out.println(_i + " , " + _j);
			
			E.method1();
			new E().method();
		}
		
	}
}
