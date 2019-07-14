package com.starry.polymorphic;

public class Test {
	public static void main(String[] args) {
		Person person ;
		person = new Man("xiaoMing");
		person.doWork();
		
		person = new Women("xiaoLi");
		person.doWork();
	}
}
