package com.starry.equals;

public class Manager extends Employee {
    int age;
	@Override
	public boolean equals(Object object) {
		if (!super.equals(object)) return false;
		
		Manager manager = (Manager) object;
		return name == manager.name && age == manager.age;
	}
}
