package com.clone.mvn_02.nio;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Reactor implements Runnable {
	public int id = 100001;
	public int bufferSize = 2048;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		init();
	}

	public void init() {
		try {
			// 创建通道和选择器
			ServerSocketChannel socketChannel = ServerSocketChannel.open();
			Selector selector = Selector.open();
			InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getLocalHost(), 4700);
			socketChannel.socket().bind(inetSocketAddress);
			// 设置通道非阻塞 绑定选择器
			socketChannel.configureBlocking(false);
			socketChannel.register(selector, SelectionKey.OP_ACCEPT).attach(id++);
			System.out.println("Server started .... port:4700");
			listener(selector);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void listener(Selector in_selector) {
		try {
			while (true) {
				Thread.sleep(1 * 1000);
				in_selector.select(); // 阻塞 直到有就绪事件为止
				Set<SelectionKey> readySelectionKey = in_selector.selectedKeys();
				Iterator<SelectionKey> it = readySelectionKey.iterator();
				while (it.hasNext()) {
					SelectionKey selectionKey = it.next();
					// 判断是哪个事件
					if (selectionKey.isAcceptable()) {// 客户请求连接
						System.out.println(selectionKey.attachment() + " - 接受请求事件");
						// 获取通道 接受连接,
						// 设置非阻塞模式（必须），同时需要注册 读写数据的事件，这样有消息触发时才能捕获
						ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
						serverSocketChannel.accept().configureBlocking(false)
								.register(in_selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE).attach(id++);
						System.out.println(selectionKey.attachment() + " - 已连接");

						// 下面这种写法是有问题的 不应该在serverSocketChannel上面注册
						/*
						 * serverSocketChannel.configureBlocking(false);
						 * serverSocketChannel.register(in_selector, SelectionKey.OP_READ);
						 * serverSocketChannel.register(in_selector, SelectionKey.OP_WRITE);
						 */
					}
					if (selectionKey.isReadable()) {// 读数据
						System.out.println(selectionKey.attachment() + " - 读数据事件");
						SocketChannel clientChannel = (SocketChannel) selectionKey.channel();
						ByteBuffer receiveBuf = ByteBuffer.allocate(bufferSize);
						clientChannel.read(receiveBuf);
						System.out.println(selectionKey.attachment() + " - 读取数据：" + getString(receiveBuf));
					}
					if (selectionKey.isWritable()) {// 写数据
						System.out.println(selectionKey.attachment() + " - 写数据事件");
						SocketChannel clientChannel = (SocketChannel) selectionKey.channel();
						ByteBuffer sendBuf = ByteBuffer.allocate(bufferSize);
						String sendText = "hello\n";
						sendBuf.put(sendText.getBytes());
						sendBuf.flip(); // 写完数据后调用此方法
						clientChannel.write(sendBuf);
					}
					if (selectionKey.isConnectable()) {
						System.out.println(selectionKey.attachment() + " - 连接事件");
					}
					// 必须removed 否则会继续存在，下一次循环还会进来,
					// 注意removed 的位置，针对一个.next() remove一次
					it.remove();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error - " + e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * ByteBuffer 转换 String
	 * 
	 * @param buffer
	 * @return
	 */
	public static String getString(ByteBuffer buffer) {
		String string = "";
		try {
			for (int i = 0; i < buffer.position(); i++) {
				string += (char) buffer.get(i);
			}
			return string;
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}
}

// NIO服务器端
