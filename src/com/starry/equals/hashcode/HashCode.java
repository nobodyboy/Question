package com.starry.equals.hashcode;

public class HashCode {
	public static void main(String[] args) {
		String str = "china";
		StringBuffer sBuffer = new StringBuffer(str);
		System.out.println(str.hashCode() + " , " + sBuffer.hashCode());
		//result: 94631255 , 366712642
		
		String stri = new String("china");
		StringBuffer sBuffer2 = new StringBuffer(stri);
		System.out.println(stri.hashCode() + " , " + sBuffer2.hashCode());
		//result: 94631255 , 1829164700
		
		String string = "china";
		System.out.println(str == stri);
		System.out.println(str==string);
	}
}
