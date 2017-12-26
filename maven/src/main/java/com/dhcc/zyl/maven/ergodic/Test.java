package com.dhcc.zyl.maven.ergodic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) throws IOException {
		String packageName = "G:\\Users\\Administrator\\workspace\\workspace\\maven\\src\\main\\java\\com\\dhcc\\zyl\\maven";
		@SuppressWarnings("unused")
		List<String> className = getClassNames(packageName);
	}

	private static List<String> getClassNames(String packageName) throws IOException {
		String pathname = "";
		List<String> list = new ArrayList<>();
		String filePath = "";
		long lines = 0L;
		if (packageName.contains("\\"))
			pathname = packageName;
		else {
			System.out.println(ClassLoader.getSystemResource("").getPath() + packageName.replace(".", "/"));
			pathname = ClassLoader.getSystemResource("").getPath() + packageName.replace(".", "/");
		}
		fileEdit(pathname, list, filePath, lines);
		return list;
	}

	// 对应文件是.java文件
	public static void fileIsJava(List<String> list, File f, String filePath, String pathname, long lines) throws IOException {
		list.add(f.getName().substring(0, f.getName().length() - 5));
		filePath = pathname + "\\" + f.getName();
		FileInputStream in = new FileInputStream(filePath);
		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		@SuppressWarnings("unused")
		String temp;
		while (null != (temp = reader.readLine())) {
			// System.out.println(temp);
			lines++;
		}
		System.out.println(f.getName() + ": " + lines + "行代码");
		lines = 0L;
	}

	// 处理文件
	public static void fileEdit(String pathname, List<String> list, String filePath, long lines) throws IOException {
		File file = new File(pathname);
		File[] contentFiles = file.listFiles();

		// 打印文件名
		if (null != contentFiles) {
			for (File f : contentFiles) {
				if (f.getName().endsWith(".java")) {
					fileIsJava(list, f, filePath, pathname, lines);
				} else if (!f.getName().contains(".")) {
					pathname += ("\\" + f.getName());
					System.out.println(pathname);
					fileEdit(pathname, list, filePath, lines);
				}
			}
		} else
			System.out.println("输入目录不存在或内容为空");

	}

}
