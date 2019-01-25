package com.protostuff.test;

import java.util.ArrayList;
import java.util.List;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * 序列化
	 * 
	 * @param pList
	 * @return
	 */
	public List<byte[]> serializeProtoStuffProductsList(List<Products> pList) {
		if (pList == null || pList.size() <= 0) {
			return null;
		}
		long start = System.currentTimeMillis();
		List<byte[]> bytes = new ArrayList<byte[]>();
		Schema<Products> schema = RuntimeSchema.getSchema(Products.class);
		LinkedBuffer buffer = LinkedBuffer.allocate(4096);
		byte[] protostuff = null;
		for (Products p : pList) {
			try {
				protostuff = ProtostuffIOUtil.toByteArray(p, schema, buffer);
				bytes.add(protostuff);
			} finally {
				buffer.clear();
			}
		}
		long end = System.currentTimeMillis();
		@SuppressWarnings("unused")
		long userTime = end - start;
		return bytes;
	}

	/**
	 * 反序列
	 * 
	 * @param bytesList
	 * @return
	 */
	public List<Products> deserializeProtoStuffDataListToProductsList(List<byte[]> bytesList) {
		if (bytesList == null || bytesList.size() <= 0) {
			return null;
		}
		long start = System.currentTimeMillis();
		Schema<Products> schema = RuntimeSchema.getSchema(Products.class);
		List<Products> list = new ArrayList<Products>();
		for (byte[] bs : bytesList) {
			Products product = new Products();
			ProtostuffIOUtil.mergeFrom(bs, product, schema);
			list.add(product);
		}
		long end = System.currentTimeMillis();
		@SuppressWarnings("unused")
		long userTime = end - start;
		return list;
	}
}
