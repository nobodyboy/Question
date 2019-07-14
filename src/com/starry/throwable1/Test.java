package com.starry.throwable1;

public class Test {
	public static void main(String[] args) {
		Test test = new Test();
		try {
			test.method1();
		} catch (DefinedCompileException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		test.method2();
	}
	// 抛出自定义的受检查异常
	void method1() throws DefinedCompileException {
		throw new DefinedCompileException();
	}
	
	// 抛出自定义的不受检查异常
	void method2() {
		throw new DefinedRuntimeException();
	}
	
}
// 自定义受检查异常
class DefinedCompileException extends Exception {
	public DefinedCompileException() {}
	public DefinedCompileException(String message) {
		super(message);
	}
}
//自定义不受检查异常
class DefinedRuntimeException extends RuntimeException {
	public DefinedRuntimeException() {}
	public DefinedRuntimeException(String message) {
		super(message);
	}
}
