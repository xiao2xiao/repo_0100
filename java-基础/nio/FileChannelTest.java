package com.neno.p15.m1509;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.SortedMap;

public class FileChannelTest {
	public static void main(String[] args) {
		fun3();
	}

	/**
	 * CharsetTest
	 */
	static void fun5() {
		SortedMap<String, Charset> map = Charset.availableCharsets();
		for (String a : map.keySet()) {
			System.out.println(a + "--->" + map.get(a));
		}
	}

	static void fun4() {
		CharBuffer buffer = CharBuffer.allocate(8);
		System.out.println(buffer.capacity());
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		buffer.put('a');
		buffer.put('b');
		buffer.put('c');
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		buffer.flip();
		System.out.println(buffer.limit());
		System.out.println(buffer.position());
		System.out.println("++++++++++++++++++++++++++");
		System.out.println(buffer.get());
		System.out.println(buffer.position());
		System.out.println("---------clear()----------");
		buffer.clear();
		System.out.println(buffer.limit());
		System.out.println(buffer.position());
		System.out.println("+++++");
		System.out.println(buffer.get(2));
		System.out.println(buffer.position());
	}

	static void fun3() {
		try (FileInputStream fileInputStream = new FileInputStream(
				"C:\\Users\\root\\Desktop\\nio\\channel\\test_02.txt");
				FileChannel fcin = fileInputStream.getChannel()) {
			ByteBuffer bBuffer = ByteBuffer.allocate(256);
			while (fcin.read(bBuffer) != -1) {
				bBuffer.flip();
				Charset charset = Charset.forName("UTF-8");
				CharsetDecoder decoder = charset.newDecoder();
				CharBuffer cBuffer = decoder.decode(bBuffer);
				System.out.println(cBuffer);
				bBuffer.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void fun2() {
		File file = new File("C:\\Users\\root\\Desktop\\nio\\channel\\test_01.txt");
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
				FileChannel rChannel = randomAccessFile.getChannel()) {
			ByteBuffer buffer = rChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
			rChannel.position(file.length());
			rChannel.write(buffer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void fun() {
		File file = new File("C:\\Users\\root\\Desktop\\nio\\channel\\test_01.txt");
		try (FileChannel inChannel = new FileInputStream(file).getChannel();
				FileChannel outChannel = new FileOutputStream("C:\\Users\\root\\Desktop\\nio\\channel\\test_02.txt")
						.getChannel();) {
			MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
			Charset charset = Charset.forName("UTF-8");
			/**
			 * 将buffer里面的数据全部输出
			 */
			outChannel.write(buffer);
			buffer.clear();
			CharsetDecoder decoder = charset.newDecoder();
			CharBuffer charBuffer = decoder.decode(buffer);
			System.out.println(charBuffer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
