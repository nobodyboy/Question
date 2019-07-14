package com.starry.serializable1;

import java.io.Serializable;

public class Person implements Serializable {
	private static final long serialVersionUID = 8580374262428896565L;
	private String name;
	private int age;
	public String height;
	
	public static int slave = 2;
	
	public Person(String name, int age, String height) {
		System.out.println("带参数构造函数");
		this.name = name;
		this.age = age;
		this.height = height;
	}
	
	public Person() {
		System.out.println("不带参数构造函数");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}
}