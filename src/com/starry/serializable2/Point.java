package com.starry.serializable2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Point {
	public static void main(String[] args) throws Exception {
		Family family = new Family(new Person("xiaoLi", 25));
		FileOutputStream fos = new FileOutputStream("E://family.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(family);
		oos.close();
		
		FileInputStream fis = new FileInputStream("E://family.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Family family_ = (Family)ois.readObject();
		System.out.println(family_.toString());
		ois.close();
	}
}

class Family implements Serializable {

	private static final long serialVersionUID = -2587442538109529888L;
	private Person person;

	public Family(Person person) {
		this.person = person;
	}
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	public String toString() {
		return "family : " + person;
	}
}


class Person {
	private String name;
	private int age;
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String toString() {
		return this.getClass().getName()+ ": " + name + ", " + age;
	}
}
