package com.starry.final1;

public class FinalTest {
	public static void main(String[] args) {
		A a = new A();
		a.num = 1;
		System.out.println("传引用前 ：" + a.num);
		
		B b = new B();
		b.method1(a);
		System.out.println("传引用后 ：" + a.num);
	}
}

class A{
	int num;
}

class B {
	public void method1(final A a) {
		a.num = 3;
		System.out.println("传引用时：" + a.num);
	}
}