package com.starry.annotation.fieldTest;

import java.lang.reflect.Field;

public class NullAbleAnnotationHandler<T> {
	void processAnnotation(T object) {
		if (null == object) {
			return;
		}
		Class<? extends Object> classT = object.getClass();
		Field[] fields = classT.getDeclaredFields();
		for (Field field : fields) {
			if (!field.isAnnotationPresent(Nullable.class)) {
				continue;  // 没有注解的属性直接跳过
			}
			Nullable nullable = field.getAnnotation(Nullable.class);
			boolean value = nullable.value();
			System.out.println(classT.getName() + " " + field.getName() + ": nullable is " + value);
			if (value) {
				continue;   // 为true或者默认  则不检查是否有值
			}
			String fieldValue = null;
			try {
				fieldValue = field.get(object).toString();
			} catch (Exception e) {
				System.out.println(e);
			}
			if (fieldValue == null) {
				// 属性不可以为空
				System.out.println("属性不可以为空");
				throw new RuntimeException("属性不可以为空");
			}
		}
	}
}
