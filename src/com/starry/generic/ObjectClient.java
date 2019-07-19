package com.starry.generic;

import java.util.ArrayList;
import java.util.List;

public class ObjectClient {
	public static void main(String[] args) {
		List<Integer> intList = new ArrayList<Integer>();
		List<? extends Object> objList = intList;
		
		objList.add(null);
		
		Integer num = intList.get(0);
	}
}
