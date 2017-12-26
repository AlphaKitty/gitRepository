package com.dhcc.zhyl.SpringBootDemo.others.annotation;

class Entity {
	private String name;
	private int age;

	public Entity(String name, int age) {
		this.name = name;
		this.age = age;
	}
}

public class InvokeDemo {
	public static void main(String[] args) throws ClassNotFoundException {
		// Class<?> clazz = Class.forName(Anno.class.getName());
		// for (Method m : clazz.getDeclaredMethods()) {
		// System.out.println(m.getName());
		// }
		// for (Annotation a : clazz.getAnnotations())
		// for (Method mm : a.annotationType().getMethods())
		// System.out.println(mm.getName());

	}
}
