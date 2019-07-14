package com.starry.polymorphic1;

public class Test1 {
	public static void main(String[] args) {	
		new Circle(5);
	}
}

class Shape {
	public Shape() {
		System.out.println("shape() before draw()");
		draw();
		System.out.println("shape() after draw()");
	}
	public void draw() {
		System.out.println("shape draw()");
	}
}

class Circle extends Shape {
	private int radius = 1;
	
	public Circle(int radius) {
		this.radius = radius;
		System.out.println("circle.circle(). radius = " + radius);
	}
	public void draw() {
		System.out.println("Circle.draw(). radius = " + radius);
	}
}

class Square extends Shape {
	public static void change() {
		System.out.println("square change");
	}
}

class Triangle extends Shape {
	public void draw() {
		System.out.println("draw triangle");
	}
	public void erase() {
		System.out.println("erase triangle");
	}
}