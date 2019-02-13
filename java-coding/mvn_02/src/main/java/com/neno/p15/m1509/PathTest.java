package com.neno.p15.m1509;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class PathTest {
	public static void main(String[] args) throws Exception {
		//fun4();
		securityVerify();
	}
	
	 public static void securityVerify() throws Exception {
	        byte[]     data = { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 };
	     // create a 64 bit secret key from raw bytes
	        SecretKey key64 = new SecretKeySpec(
	                   new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 },
	                   "Blowfish");
	        
	     // create a cipher and attempt to encrypt the data block with our key
	        Cipher     c = Cipher.getInstance("Blowfish/ECB/NoPadding");
	        c.init(Cipher.ENCRYPT_MODE, key64);
	        c.doFinal(data);
	        System.out.println("64 bit test: passed");
	        
	     // create a 192 bit secret key from raw bytes
	        SecretKey key192 = new SecretKeySpec(
	                    new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
	                                 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f,
	                                 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17 },
	                    "Blowfish");
	 
	        // now try encrypting with the larger key
	        c.init(Cipher.ENCRYPT_MODE, key192);
	        c.doFinal(data);
	 
	        System.out.println("192 bit test: passed");
	 
	        System.out.println("Tests completed");
	    }

	/**
	 * AttributeViewTest
	 */
	static void fun5() {
		try {
			// 获取将要操作的文件
			Path testPath = Paths.get("AttributeViewTest.java");
			// 获取访问基本属性的BasicFileAttributeView
			BasicFileAttributeView basicView = Files.getFileAttributeView(testPath, BasicFileAttributeView.class);
			// 获取访问基本属性的BasicFileAttributes
			BasicFileAttributes basicAttribs = basicView.readAttributes();
			// 访问文件的基本属性
			System.out.println("创建时间：" + new Date(basicAttribs.creationTime().toMillis()));
			System.out.println("最后访问时间：" + new Date(basicAttribs.lastAccessTime().toMillis()));
			System.out.println("最后修改时间：" + new Date(basicAttribs.lastModifiedTime().toMillis()));
			System.out.println("文件大小：" + basicAttribs.size());
			// 获取访问文件属主信息的FileOwnerAttributeView
			FileOwnerAttributeView ownerView = Files.getFileAttributeView(testPath, FileOwnerAttributeView.class);
			// 获取该文件所属的用户
			System.out.println(ownerView.getOwner());
			// 获取系统中guest对应的用户
			UserPrincipal user = FileSystems.getDefault().getUserPrincipalLookupService()
					.lookupPrincipalByName("guest");
			// 修改用户
			ownerView.setOwner(user);
			// 获取访问自定义属性的FileOwnerAttributeView
			UserDefinedFileAttributeView userView = Files.getFileAttributeView(testPath,
					UserDefinedFileAttributeView.class);
			List<String> attrNames = userView.list();
			// 遍历所有的自定义属性
			for (String name : attrNames) {
				ByteBuffer buf = ByteBuffer.allocate(userView.size(name));
				userView.read(name, buf);
				buf.flip();
				String value = Charset.defaultCharset().decode(buf).toString();
				System.out.println(name + "--->" + value);
			}
			// 添加一个自定义属性
			userView.write("发行者", Charset.defaultCharset().encode("疯狂Java联盟"));
			// 获取访问DOS属性的DosFileAttributeView
			DosFileAttributeView dosView = Files.getFileAttributeView(testPath, DosFileAttributeView.class);
			// 将文件设置隐藏、只读
			dosView.setHidden(true);
			dosView.setReadOnly(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * WatchServiceTest
	 */

	static void fun4() {
		try {
			WatchService watchService = FileSystems.getDefault().newWatchService();
			Paths.get("C:", "Users", "root", "Desktop", "nio").register(watchService,
					StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
					StandardWatchEventKinds.ENTRY_MODIFY);
			while (true) {
				/**
				 * poll如果没有就返回null，take是不停的监控
				 */
				WatchKey key = watchService.take();
				for (WatchEvent<?> event : key.pollEvents()) {
					System.out.println(event.context() + " 文件发生了" + event.kind() + "事件！");
				}
				boolean flag = key.reset();
				if (!flag) {
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * FileVistorTest
	 */
	static void fun3() {

		try {
			Files.walkFileTree(Paths.get("C:", "Users", "root", "Desktop", "nio"), new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					System.out.println("正在访问：" + file + " 文件");
					if (file.endsWith("test_04.txt")) {
						System.out.println("已经找到。。。。");
						return FileVisitResult.TERMINATE;
					}
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
					System.out.println("正在访问：" + dir + " 路径");
					return FileVisitResult.CONTINUE;
				}

			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * FilesTest
	 */
	static void fun2() {
		try {
			Path path = Paths.get("C:\\\\Users\\\\root\\\\Desktop\\\\nio\\\\channel\\\\test_01.txt");
			Path path2 = Paths.get("C:\\\\Users\\\\root\\\\Desktop\\\\nio\\\\channel\\\\test_04.txt");
			// copy文件
//			Files.copy(path,
//					new FileOutputStream("C:\\Users\\root\\Desktop\\nio\\channel\\test_03.txt"));
			System.out.println("test_01.txt是否为隐藏文件：" + Files.isHidden(path));

			List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));
			System.out.println(lines);
			System.out.println(Files.size(path));

			List<String> poems = new ArrayList<>();
			poems.add("中国人民喜爱你");
			poems.add("非常好，很好啊");
			Files.write(path2, poems, Charset.forName("UTF-8"));
			System.out.println("++++++++++++");
			Files.list(Paths.get(".")).forEach(a -> System.out.println(a));
			System.out.println("+++++++++++++=");
			Files.lines(path2, Charset.forName("UTF-8")).forEach(b -> System.out.println(b));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * path里面包含的路径数量：1 path的根路径：null D:\002---AllCode\eclipse\wrokspace_1\mvn_02\.
	 * D:\ 5 mvn_02 D:\pb\aa
	 */
	static void fun() {
		Path path = Paths.get(".");
		System.out.println("path里面包含的路径数量：" + path.getNameCount());
		System.out.println("path的根路径：" + path.getRoot());
		/**
		 * path的绝对路径
		 */
		Path absolutePath = path.toAbsolutePath();
		System.out.println(path.toAbsolutePath());
		System.out.println(absolutePath.getRoot());

		System.out.println(absolutePath.getNameCount());

		System.out.println(absolutePath.getName(3));

		Path path2 = Paths.get("D:", "pb", "aa");
		System.out.println(path2);
	}
}
