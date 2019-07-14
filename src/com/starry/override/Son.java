package com.starry.override;

public class Son extends Father {

	// 表明父类的静态属性和静态方法可以被子类继承
	public static void sonMethod() {
		System.out.println(string);
	    methond1();
	}
	
	// 表明静态方法不能被子类重写
	//@Override
	protected static void methond1() {
		System.out.println("son method1");
	}
	
	private void doSomething() {
		super.string = "abcd";
		System.out.println(string);
	}
	
	public static void main(String[] args) {
		
		Father father = new Father();
		Father son = new Son();    // 动态绑定
		Son son1 = new Son();
		
		father.methond1();   // father method1
		son.methond1();      // father method1
		son1.methond1();     // son method1
		
		son1.doSomething();
	}
}
