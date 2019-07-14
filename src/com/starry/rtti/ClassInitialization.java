package com.starry.rtti;

import java.util.Random;

class Initable {
	static final int staticFinal = 10;
	static final int staticFinal2 = ClassInitialization.random.nextInt(100);
	static {
		System.out.println("Initializing Initable");
	}
}
class Initable2 {
	static int staticNonFinal = 100;
	static {
		System.out.println("Initializing Initable2");
	}
}
class Initable3 {
	static int staticNonFinal = 11;
	static {
		System.out.println("Initializing Initable3");
	}
}
public class ClassInitialization {
	public static Random random = new Random(100);
	public static void main(String[] args) throws Exception {
		Class<?> initable = Initable.class;
		System.out.println("创建Initable类的class对象引用后...");
		System.out.println(Initable.staticFinal);
		System.out.println(Initable.staticFinal2);
		System.out.println("======================");
		System.out.println(Initable2.staticNonFinal);
		System.out.println("======================");
		Class<?> initable3 = Class.forName("com.starry.rtti.Initable3");
		System.out.println("创建Initable3类的class对象引用后....");
		System.out.println(Initable3.staticNonFinal);
	}
}
