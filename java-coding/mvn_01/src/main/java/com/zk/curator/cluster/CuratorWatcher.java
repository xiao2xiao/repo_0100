package com.zk.curator.cluster;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class CuratorWatcher {
	/**
	 * 父节点path
	 */
	static final String PARENT_PATH = "/super";

	/**
	 * zookeeper地址
	 */
	static final String CONNECT_ADDR = "192.168.254.134:2181";
	/**
	 * session超时时间 ms
	 */
	static final int SESSION_OUTTIME = 5000;

	public static CuratorFramework createCuratorFramework() {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
		CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(CONNECT_ADDR)
				.connectionTimeoutMs(SESSION_OUTTIME).retryPolicy(retryPolicy).build();
		return curatorFramework;
	}

	@SuppressWarnings("resource")
	public CuratorWatcher() throws Exception {

		CuratorFramework cf = createCuratorFramework();
		// 3 建立连接
		cf.start();
		// 4 创建跟节点
		if (cf.checkExists().forPath(PARENT_PATH) == null) {
			cf.create().withMode(CreateMode.PERSISTENT).forPath(PARENT_PATH, "super init".getBytes());
		}

		// 4 建立一个PathChildrenCache缓存,第三个参数为是否接受节点数据内容 如果为false则不接受
		PathChildrenCache cache = new PathChildrenCache(cf, PARENT_PATH, true);
		// 5 在初始化的时候就进行缓存监听
		cache.start(StartMode.POST_INITIALIZED_EVENT);
		cache.getListenable().addListener(new PathChildrenCacheListener() {
			/**
			 * <B>方法名称：</B>监听子节点变更<BR>
			 * <B>概要说明：</B>新建、修改、删除<BR>
			 * 
			 * @see org.apache.curator.framework.recipes.cache.PathChildrenCacheListener#childEvent(org.apache.curator.framework.CuratorFramework,
			 *      org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent)
			 */
			@Override
			public void childEvent(CuratorFramework cf, PathChildrenCacheEvent event) throws Exception {
				switch (event.getType()) {
				case CHILD_ADDED:
					System.out.println("CHILD_ADDED :" + event.getData().getPath());
					System.out.println("CHILD_ADDED :" + new String(event.getData().getData()));
					break;
				case CHILD_UPDATED:
					System.out.println("CHILD_UPDATED :" + event.getData().getPath());
					System.out.println("CHILD_UPDATED :" + new String(event.getData().getData()));
					break;
				case CHILD_REMOVED:
					System.out.println("CHILD_REMOVED :" + event.getData().getPath());
					System.out.println("CHILD_REMOVED :" + new String(event.getData().getData()));
					break;
				default:
					break;
				}
			}
		});
	}
}
