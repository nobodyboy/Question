package com.starry.equals;

public class Test {
	public static void main(String[] args) {
		Employee employee1 = new Employee();
		Employee employee2 = new Manager();
		Manager manager = new Manager();
		
		employee1.name = "xiaoLi";
		employee2.name = "xiaoHua";
		manager.name = "xiaoLi";
		manager.age = 20;
		
		//System.out.println(employee1.equals(manager));
		System.out.println(manager.equals(employee1));
		
		
		Employee employee3 = new Employee();
		employee3.name = "11";
		Employee employee4 = new Employee();
		employee4.name = "11";
		System.out.println(employee3.equals(employee4));
		System.out.println(employee3.hashCode() + ",  " + employee4.hashCode());
		System.out.println(employee3==employee4);
	}
}
