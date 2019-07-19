package com.starry.generic.shape;

import java.util.ArrayList;
import java.util.List;

public class Client {
	public static void main(String[] args) {
		Canvas canvas = new Canvas();
		List<Shape> shapeList = new ArrayList<Shape>();
		shapeList.add(new Square());
		shapeList.add(new Circle());
		canvas.drawAll(shapeList);
		
		List<Circle> circleList = new ArrayList<Circle>();
		circleList.add(new Circle());
		canvas.drawAll(circleList);     // 编译报错
		
		canvas.draw(new Circle());
		
		List<? extends Shape> list = new ArrayList<Shape>();
//		list.add(new Circle());
		
		canvas.addShape(shapeList, new Square());
		canvas.addShape(shapeList, new Circle());
		
		List<Integer> intList = new ArrayList<Integer>();
		canvas.addShape(intList, new Integer(1));
		
		List<String> strList = new ArrayList<String>();
		System.out.println(intList.getClass() == strList.getClass());
		
	}
	
}
