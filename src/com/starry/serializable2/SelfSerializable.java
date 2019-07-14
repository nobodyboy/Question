package com.starry.serializable2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SelfSerializable {
	public static void main(String[] args) throws Exception {
		D d = new D("name", "password");
		FileOutputStream fos = new FileOutputStream("E://D.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(d);
		oos.close();
		
		FileInputStream fis = new FileInputStream("E://D.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		D d_ = (D)ois.readObject();
		System.out.println(d_.toString());
		ois.close();
	}
}
class D implements Serializable {
	private String i;
	private transient String j;
	
	public D(String i, String j) {
		this.i = "non transient: " + i;
		this.j = "transient: " + j;
	}
	public String toString() {
		return i + ", " + j;
	}
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject();
		oos.writeObject(j);
	}
	
	private void readObject(ObjectInputStream ois) throws Exception {
		ois.defaultReadObject();
		System.out.println("readObject before: " + j);
		j = (String)ois.readObject();
		System.out.println("readObject after: " + j);
	}
}