package com.dhcc.zhyl.SpringBootDemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Format {

	/**
	 * 格式化数据库参数
	 * @param origin
	 * @return
	 */
	public String sqlFormat(String origin) {
		origin = origin.replaceAll("\\(String\\)", "'");
		origin = origin.replaceAll("\\(Long\\)", "");
		origin = origin.replaceAll("\\(BigDecimal\\)", "");
		StringBuilder sb = new StringBuilder(origin);
		// 创建正则表达式 [, 开头~ 非空格的多个字符为内容~ (String)结尾]
		Pattern pattern = Pattern.compile(", ([^\\s]*)\'", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(origin);
		while (matcher.find()) {
			sb.replace(matcher.start() + 1, matcher.start() + 2, "'");
		}
		System.out.println(sb);
		return sb.toString();
	}

	/**
	 * 报文转换 (本地->服务器)
	 * @param origin
	 * @return
	 * @throws IOException 
	 */
	@SuppressWarnings("resource")
	public String contentFormat(String path) throws IOException {
		File file = new File(path);
		String temp = "";
		String comb = "";
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		while ((temp = bufferedReader.readLine()) != null) {
			temp = temp.replaceAll("\"", "\\\\\"");
			comb += temp;
		}
		System.out.println(comb);
		return comb;
	}

	public static void main(String[] args) throws IOException {
		Format format = new Format();
		format.sqlFormat(
				"null, null, null, null, 20171223(String), IBS20171223000001113796(String), null, null, null, null, null, null, 20171223(String), 135137(String), IBS20171223000001113796(String), 20140724(String), 030750(String), 1514008293816190(String), C007(String), 6101022(String), null, null, MS01(String), null, null, null, 90001(String), 90001(String), java.io.StringReader@6c5a2133(StringReader), null");
		// 这个txt文件里放入报文体内容
		// format.contentFormat("G:\\Users\\Administrator\\Desktop\\content.txt");
	}
}
