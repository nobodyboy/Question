package com.starry.finalize1;

public class FinalizeTest {
	public static void main(String[] args) {
		FinalizeTest finalizeTest = new FinalizeTest();
		finalizeTest = null;
		System.gc();
	}

	@Override
	protected void finalize() throws Throwable {
		// doSomething
		System.out.println("释放非内存资源");
		super.finalize();
	}
}
