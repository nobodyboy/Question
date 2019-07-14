package com.starry.rtti;

class A {
	static { System.out.println("A static");}
}
class B {
	static { System.out.println("B static");}
}
class C {
	static { System.out.println("C static");}
}
public class Test1 {
	public static void main(String[] args) {
		A a = new A();
		try {
			Class<?> cs = Class.forName("com.starry.rtti.B"); // 全路径类名
			System.out.println(cs.toString());
			
			Class<?> cs1 = a.getClass();
			System.out.println(cs1.toString());
			
			Class<?> cs2 = A.class;
			System.out.println(cs2.toString());
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		new C();
	}
}
