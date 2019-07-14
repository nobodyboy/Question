package com.starry.annotation.classTest;

import java.util.HashMap;
import java.util.Map;


public class ShardMappingAnnotationHandler {
	static Map<String, String> shardMap = new HashMap<String, String>(8);
	void processAnnotation(Object object) {
		if (object == null) {
			return;
		}
		Class<?> classObject = object.getClass();
		
		if (!classObject.isAnnotationPresent(ShardMapping.class)) {
			return;   // object对象没有被ShardMapping注解修饰
		}
		String className = classObject.getName();
		ShardMapping shardMapping = classObject.getAnnotation(ShardMapping.class);
		String value = shardMapping.value();
		if ("".equals(value)) {
			System.out.println(className + "： shardMapping value is default, go on...");
			shardMap.put(className, "defaule");
		} else if ("hashCode".equals(value)) {
			System.out.println(className + "： shardMapping value is hashCode, go on...");
			shardMap.put(className, "hashCode");
		} else if ("shardRef".equals(value)) {
			System.out.println(className + "： shardMapping value is shardRef, go on...");
			shardMap.put(className, "shardRef");
		} else {
			throw new RuntimeException(className + "： shardMapping value illegal. game over...");
		}
	}
}
