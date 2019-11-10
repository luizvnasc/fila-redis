package org.fila.redis.dao;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

import redis.clients.jedis.Jedis;

@Singleton
public class JedisPool {

	
	private static redis.clients.jedis.JedisPool jedisPool;
	
	@PostConstruct
	private void init() {
		jedisPool = new redis.clients.jedis.JedisPool("localhost:32768");
	}
	
	public Jedis getResource() {
		return jedisPool.getResource();
	}
	
}
