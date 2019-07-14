package com.starry.rtti;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectTest {
	public static void main(String[] args) {
		Man man = new Man("xiaoLi", 18, "xxx@qq.com");
		Class<?> manClass = man.getClass();
		
		System.out.println("======public域的属性=======");
		Field[] fileds = manClass.getFields();
		for (Field field : fileds) {
			System.out.println(field.getName());
		}
		System.out.println("======所有声明的属性========");
		Field[] declaredFileds = manClass.getDeclaredFields();
		for (Field field : declaredFileds) {
			System.out.println(field.getName());
		}
		System.out.println("======public域的方法，包括父类的方法=======");
		Method[] methods = manClass.getMethods();
		for (Method method : methods) {
			System.out.println(method.getName());
		}
		System.out.println("======Class对象引用指向的类的方法=======");
		Method[] declaredMethods = manClass.getDeclaredMethods();
		for (Method method : declaredMethods) {
			System.out.println(method.getName());
		}
		System.out.println("======构造方法=======");
		Constructor<?>[] constructors = manClass.getConstructors();
		for (Constructor constructor : constructors) {
			System.out.println(constructor.getName());
			System.out.println(constructor.getParameterCount());
		}
		System.out.println("======构造方法，包括私有构造函数=======");
		Constructor<?>[] declaredConstructors = manClass.getDeclaredConstructors();
		for (Constructor constructor : declaredConstructors) {
			System.out.println(constructor.getName());
			System.out.println(constructor.getParameterCount());
		}
	}
}
class Man {
	private String name;
	private int age;
	public String address;
	private Man() {}
	public Man(String name, int age, String address) {
		this.name = name;
		this.age = age;
		this.address = address;
	}
	private void privateMthod() {
		System.out.println("private method");
	}
	public void publicMehtod() {
		System.out.println("public method");
	}
}