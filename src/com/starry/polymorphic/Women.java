package com.starry.polymorphic;

public class Women extends Person {
	public Women(String name) {
		setName(name);
	}
	@Override
	public void doWork() {
		System.out.println("woMen: " + getName() + "£º doing work...");
	}
}
