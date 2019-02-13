package com.neno.p15.m1503;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileInputStreamTest {
	public static void main(String[] args) throws Exception {
		// fun1("C:\\Users\\root\\Desktop\\", "待完成的任务.txt", "newfile.txt");
	 //fun2("C:\\Users\\root\\Desktop\\", "待完成的任务.txt", "newfile.txt");
		insertContent("C:\\Users\\root\\Desktop\\", "newfile.txt", 100, "\r\n插入的内容\r\n");
	}

	static void fun1(String path, String source, String des) {
		File file = new File(path + source);
		File file2 = new File(path + des);
		try {
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(file2);
			byte[] buf = new byte[1024];
			int hasRead = 0;
			while ((hasRead = fis.read(buf)) > 0) {
				fos.write(buf, 0, hasRead);
			}
			fos.close();
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * RandomAccessFileTest
	 */
	static void fun2(String path, String source, String des) {
		File file = new File(path + source);
		File file2 = new File(path + des);
		try (RandomAccessFile raf = new RandomAccessFile(file, "r");
				RandomAccessFile raf2 = new RandomAccessFile(file2, "rw")) {
			System.out.println("pathname文件的初始位置：" + raf.getFilePointer());
			long pos = raf2.length();
			System.out.println("raf2：" + pos);
			int hasRead = 0;
			// raf.seek(100);
			raf2.seek(pos);
			byte[] b = new byte[256];
			while ((hasRead = raf.read(b)) > 0) {
				System.out.println(new String(b, 0, hasRead));
				// raf2.seek(raf2.getFilePointer());
				raf2.write(b, 0, hasRead);
			}
			raf2.close();
			raf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 在指定位置插入内容 应该保存插入位置之后的内容，否则在指定位置插入之后会覆盖之后的内容
	 */
	static void insertContent(String path, String file, long pos, String content) {
		try {
			File tmp = File.createTempFile(path + "tmp", null);
			tmp.deleteOnExit();
			RandomAccessFile raf = new RandomAccessFile(path + file, "rw");
			RandomAccessFile raf2 = new RandomAccessFile(tmp, "rw");// 保存插入位置之后的内容
			raf.seek(pos);
			int hasRead = 0;
			byte[] b = new byte[512];
			while ((hasRead = raf.read(b)) > 0) {
				System.out.println(new String(b, 0, hasRead));
				raf2.write(b, 0, hasRead);
			}
			raf.seek(pos);
			System.out.println("raf_pointer1:"+raf.getFilePointer());
			raf.write(content.getBytes("UTF-8"));
			System.out.println("raf_pointer2:"+raf.getFilePointer());
			hasRead = 0;
			raf2.seek(0);
			while ((hasRead = raf2.read(b)) > 0) {
				raf.write(b, 0, hasRead);
			}
			raf2.close();
			raf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
