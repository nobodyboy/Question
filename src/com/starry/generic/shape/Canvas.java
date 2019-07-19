package com.starry.generic.shape;

import java.util.List;

public class Canvas {
	public void draw(Shape shape) {
		shape.draw(this);
	}
	
	public void drawAll(List<? extends Shape> shapes) {
		for (Shape shape : shapes) {
			shape.draw(this);
		}
	}
	
	public <T> void addShape(List<T> list, T t) {
		list.add(t);
	}
}
