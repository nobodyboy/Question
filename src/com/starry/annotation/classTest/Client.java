package com.starry.annotation.classTest;

public class Client {
	public static void main(String[] args) {
		DeparementTable deparementTable = new DeparementTable();
		EmployeeTable employeeTable = new EmployeeTable();
		
		ShardMappingAnnotationHandler handler = new ShardMappingAnnotationHandler();
		handler.processAnnotation(deparementTable);
		handler.processAnnotation(employeeTable);
	}
}
