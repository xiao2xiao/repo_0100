package com.neno.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		File file = new File("C:\\Users\\root\\Desktop\\point.txt");
		if (file.exists()) {
			InputStream input = new FileInputStream(file);
			byte[] data = new byte[1024];
			int len = input.read(data);
			System.out.println(data);
			System.out.println(new String(data, 0, len));
		}
	}
}
