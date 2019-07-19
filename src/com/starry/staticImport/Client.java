package com.starry.staticImport;
import static com.starry.staticImport.StaticTest.method1;

public class Client {
	public static void main(String[] args) {
		method1();   // 
		StaticTest.method2();
	}
}
