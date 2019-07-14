package com.starry.annotation.fieldTest;

public class Client {
	public static void main(String[] args) {
		Student xiaoLi = new Student();
		xiaoLi.setId("001");
		xiaoLi.setName("xiaoLi");
		xiaoLi.setSex("man");
		
		Student xiaoSi = new Student();
		xiaoSi.setName("xiaoSi");
		xiaoSi.setId(null);
		
		NullAbleAnnotationHandler<Student> handler = new NullAbleAnnotationHandler<Student>();
		handler.processAnnotation(xiaoLi);
		handler.processAnnotation(xiaoSi);
	}
}
