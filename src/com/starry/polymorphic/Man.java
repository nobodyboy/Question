package com.starry.polymorphic;

public class Man extends Person {
	public Man(String name) {
		setName(name);
	}
	@Override
	public void doWork() {
		System.out.println("man: " + getName() + " doing work...");
	}
}
