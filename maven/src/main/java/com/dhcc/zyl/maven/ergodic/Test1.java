package com.dhcc.zyl.maven.ergodic;

import java.io.File;

public class Test1 {
	public static void main(String[] args) {
		String path = "G:\\Users\\Administrator\\workspace\\workspace\\maven\\src\\main\\java\\com\\dhcc\\zyl\\maven";
		scanPath(path);
	}

	// 扫描路径
	private static void scanPath(String path) {
		File file = new File(path);// 根据路径找到目录 这个返回值不可能为null
		File[] files = file.listFiles();// 列出目录包含的所有子目录或文件
		String profix = "";
		if (null != files) {
			for (File f : files) {
				if (f.isDirectory()) {
					profix = profix + "\\" + f.getName();
					String newPath = path + "\\" + f.getName();
					System.out.println(profix);
					scanPath(newPath);
				} else
					System.out.println(f.getName());
			}
		} else
			System.out.println("目录不存在或为空");
	}
}
