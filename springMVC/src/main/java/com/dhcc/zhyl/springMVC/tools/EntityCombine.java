package com.dhcc.zhyl.springMVC.tools;

import java.lang.reflect.Field;

public class EntityCombine {

	/**
	 * 组合两个相同类型的实例 第二个参数的非空数据加到第一个参数上 因此参数有先后顺序 第一个参数用来返回
	 * 
	 * @param targer
	 * @param origin
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static Object combine(Object targer, Object origin) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InstantiationException {
		if (!targer.getClass().getName().equals(origin.getClass().getName()))
			throw new ClassFormatError("两个实例类型不一致");
		Class<?> clazzA = Class.forName(targer.getClass().getName());
		Class<?> clazzB = Class.forName(origin.getClass().getName());
		Field[] as = clazzA.getDeclaredFields();
		Field[] bs = clazzB.getDeclaredFields();
		for (int i = 0; i < as.length; i++) {
			as[i].setAccessible(true);
			bs[i].setAccessible(true);
			if (null == as[i].get(targer))
				as[i].set(targer, bs[i].get(origin));
		}
		return targer;
	}
}
