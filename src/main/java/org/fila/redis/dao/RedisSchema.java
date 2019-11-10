package org.fila.redis.dao;

public class RedisSchema {
	
	public static String getFilaKey() {
		return "svd:fila";
	}
	
	public static String headKey() {
		return "svd:fila:head";
	}

	public static String getNextID(String id) {
		if(id == null) {
			return "0-0";
		}
		int tail = Integer.parseInt(id.split("-")[1]);
		id = id.split("-")[0] + "-" + ++tail;
		return id;
		
	}
}
