package com.starry.equals;

public class Employee {
	String name;
	String salary;
	
	@Override
	public boolean equals(Object object) {
		if (this == object) return true;  // 与自身比较返回true,同时处理null与null的比较
		
		if (object == null) return false;
		
		//if (!(object instanceof Employee)) return false;
		
		if (getClass() != object.getClass()) return false;
		
		Employee employee = (Employee)object;
		if (this.name == employee.name) return true;
		
		return false;
	}
}
