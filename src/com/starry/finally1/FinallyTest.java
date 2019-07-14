package com.starry.finally1;

public class FinallyTest {
	
	public static void main(String[] args) {
		System.out.println("method() : " + method());
		System.out.println();
		System.out.println("method1() : " + method1());
		System.out.println();
		System.out.println("method2() : " + method2());
		System.out.println();
		System.out.println("method3() : " + method3().num);
		System.out.println();
		System.out.println("method4() : " + method4().num);
		System.out.println();
		System.out.println("method5() : " + method5().num);
	}

	private static int method() {

		int i = 1;
		try {
			i = 3;
			System.out.println("method()-try : i = " + i);
			return i;
		} finally {
			i = 7;
			System.out.println("method()-finally : i = " + i);
		}

	}

	private static int method1() {
		int i = 1;
		try {
			i = 3;
			System.out.println("method1()-try : i = " + i);
			throw new Exception();
			//return i;
		} catch (Exception e) {
			i = 5;
			System.out.println("method1()-catch : i = " + i);
			return i;
		} finally {
			i = 7;
			System.out.println("method1()-finally : i = " + i);
		}
	}
	
	@SuppressWarnings("finally")
	private static int method2() {

		int i = 1;
		try {
			i = 3;
			System.out.println("method2()-try : i = " + i);
			return i;
		} finally {
			i = 7;
			System.out.println("method2()-finally : i = " + i);
			return i;
		}

	}
	
	private static A method3() {
		A a = new A();
		a.num = 1;
		try {
			a.num = 3;
			System.out.println("method3()-try : a.num = " + a.num);
			return a;
		} finally {
			a.num = 7;
			System.out.println("method3()-finally : a.num = " + a.num);
		}
	}
	
	private static A method4() {
		A a = new A();
		a.num = 1;
		try {
			a.num = 3;
			System.out.println("method4()-try : a.num = " + a.num);
			throw new Exception();
			//return a.num;
		} catch (Exception e) {
			a.num = 5;
			System.out.println("method4()-catch : a.num = " + a.num);
			return a;
		} finally {
			a.num = 7;
			System.out.println("method4()-finally : a.num = " + a.num);
		}
	}
	
	@SuppressWarnings("finally")
	private static A method5() {
		A a = new A();
		a.num = 1;
		try {
			a.num = 3;
			System.out.println("method5()-try : a.num = " + a.num);
			return a;
		} finally {
			a.num = 7;
			System.out.println("method5()-finally : a.num = " + a.num);
			return a;
		}
	}
}

class A {
	int num;
}
