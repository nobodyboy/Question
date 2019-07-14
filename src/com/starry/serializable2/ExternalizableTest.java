package com.starry.serializable2;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ExternalizableTest {
	public static void main(String[] args) throws Exception {
		B b = new B(2, "helloWorld");
		FileOutputStream fos = new FileOutputStream("E://B.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(b);
		oos.close();
		
		FileInputStream fis = new FileInputStream("E://B.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		B b_ = (B)ois.readObject();
		System.out.println(b_.toString());
		ois.close();
		
//		C c = new C("xiaoLi", "123456");
//		FileOutputStream fos = new FileOutputStream("E://C.txt");
//		ObjectOutputStream oos = new ObjectOutputStream(fos);
//		oos.writeObject(c);
//		oos.close();
//		
//		FileInputStream fis = new FileInputStream("E://C.txt");
//		ObjectInputStream ois = new ObjectInputStream(fis);
//		C c_ = (C)ois.readObject();
//		System.out.println(c_.toString());
//		ois.close();
	}
}

class B implements Externalizable {
	private int i;
	private String str;
	
	public B() {
		System.out.println("B : 不带参数的构造方法");
	}
	
	public B(int i, String str) {
		this.i = i;
		this.str = str;
		System.out.println("B : 带参数的构造方法");
	}
	
	@Override
	public String toString() {
		return str + "  " +i;
	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("B.writeExternal");
		out.writeObject(str);
		out.writeInt(i);
	}
	
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		System.out.println("B.readExternal");
		str = (String)in.readObject();
		i = in.readInt();
	}
}

class C implements Serializable{
	private String name;
	private transient String passWord;
	
	public C (String name, String passWord) {
		this.name = name;
		this.passWord = passWord;
	}
	
	public String toString() {
		return name + " " + passWord;
	}
}
