package com.starry.serializable2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TypeA {
	public static void main(String[] args) throws Exception {
		// 序列化
		System.out.println("..............序列化开始..............................");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("e://A.txt"));
        A a = new A(2);
        oos.writeObject(a);
        oos.flush();
        oos.close();
        System.out.println("..............序列化结束..............................");
        
        //反序列化
        System.out.println("..............反序列化开始..............................");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("e://A.txt"));
        A a_ = (A) ois.readObject();
        ois.close();
        System.out.println(a_.getI());
        System.out.println("..............反序列化结束..............................");
	}
}

class A implements Serializable {
	private int i;
	
	public int getI() {
		return i;
	}
	
	public A() {
		System.out.println("A : 不带参数的构造方法");
	}
	
	public A(int i) {
		this.i = i;
		System.out.println("A: 带参数的构造方法");
	}
}