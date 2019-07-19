package com.starry.generic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Client {
	public static void main(String[] args) {
		List list = new ArrayList();
		list.add(new Integer(1));
		list.add("2");
		
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			Object object = iterator.next();
			System.out.println(object);
		}
		
		List<Integer> intList = new ArrayList<Integer>();
		intList.add(new Integer(2));
		intList.add(new Integer(6));
		//intList.add("3");  // 不符合类型要求  编译报错
		
		for (int i = 0; i < intList.size(); i++ ) {
			Integer integer = intList.get(i);  // 直接获取Integer
			System.out.println(integer);
		}
	}
}
