package com.clone.mvn_03.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BeanUtils {
	@SuppressWarnings("unchecked")
	public static <T> T cloneTo(T t) {
		T dist = null;
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		ObjectOutputStream objOutStream = null;
		ObjectInputStream objInStream = null;
		try {
			objOutStream = new ObjectOutputStream(byteStream);
			objOutStream.writeObject(t);
			objOutStream.flush();
			objInStream = new ObjectInputStream(new ByteArrayInputStream(byteStream.toByteArray()));
			dist = (T) objInStream.readObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (objOutStream != null) {
				try {
					objOutStream.close();
					objOutStream = null;
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			if (objInStream != null) {
				try {
					objInStream.close();
					objInStream = null;
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return dist;
	}

	public static void main(String[] args) {
		/** * 单个对象的克隆 */
		User user = new User();
		user.setId(2);
		user.setAge(22);
		user.setName("jack");
		User user1 = cloneTo(user);
		user1.setAge(33);
		System.out.println(user.getAge()); // 22
		System.out.println(user1.getAge()); // 33

		System.out.println(
				"========================================================================================================");
		/** * 深度克隆（Mysystem中有User这个属性） */
		MySystem system = new MySystem();
		system.setId(1);
		system.setName("这是一个系统");
		system.setUser(user);
		MySystem system1 = cloneTo(system);
		system1.setName("这还是我的系统");
		System.out.println(system.getName()); // 这是一个系统
		System.out.println(system1.getName()); // 这还是我的系统
		System.out.println(system1.getUser().getName());
		System.out.println(
				"========================================================================================================");
		/** * 深度克隆之集合的深度克隆 */
		User userMain = new User();
		user.setId(2);
		user.setAge(22);
		user.setName("jackMain");
		User userBranch = new User();
		userBranch.setId(2);
		userBranch.setAge(22);
		userBranch.setName("jackBranch");
		MySystem systemMain = new MySystem();
		systemMain.setName("这还是我的系统Main");
		List<User> users = new ArrayList<>();
		users.add(userMain);
		systemMain.setUsers(users);
		MySystem systemBranch = cloneTo(systemMain);
		systemBranch.setName("这还是我的系统Branch");
		systemBranch.getUsers().add(userBranch);
		System.out.println(systemMain.getName() + ":" + systemMain.getUsers().size()); // 1
		System.out.println(systemBranch.getName() + ":" + systemBranch.getUsers().size());// 2

	}
}
