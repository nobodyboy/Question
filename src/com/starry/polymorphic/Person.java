package com.starry.polymorphic;

public abstract class Person {
	private String name;
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public abstract void doWork();
}
