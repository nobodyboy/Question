package com.starry.rtti;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class Person {
	int i = 0;
	void method1() {}
	static void method2() {}
}
class Son extends Person {
	int j = 0;
	String str = "see you";
	void method3() {}
	static void method4() {}
}
public class ClassFunction {
	public static void main(String [] args) throws Exception {
		@SuppressWarnings("unchecked")
		Class<? extends Person> class1 = (Class<? extends Person>) Class.forName("com.starry.rtti.Son");
		Person person = (Son)class1.newInstance();
		System.out.println(class1.getSimpleName());
		System.out.println(class1.getSuperclass());
		
		Field[] filedsFields = class1.getFields();
		Method[] methods = class1.getMethods();
		Constructor<?>[] constructors = class1.getConstructors();
		
		
	}
}
