package com.versawork.http.caching.files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisCluster;

//@Component
public class SetCacheData implements Runnable {

	static Logger LOGGER = LoggerFactory.getLogger("CACHEFILE");

	private JedisFactory jedisFactory;
	private String key;
	private String json;

	public SetCacheData() {

	}

	public SetCacheData(String key, String json, JedisFactory jedisFactory) {
		/*
		 * Receive a Json of the query fetched from DB for the unitNumber and
		 * put it to Redis
		 */
		this.key = key;
		this.json = json;
		this.jedisFactory = jedisFactory;
	}

	@Override
	public void run() {
		try {
			JedisCluster jCluster = (JedisCluster) jedisFactory.getJedisConnectionFactory();
			jCluster.set(key, json);
			jCluster.expire(key, JedisFactory.cacheTTL);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("SetCacheData Failed to set key " + key + " in cache." + e.getMessage());
		}

	}

}
