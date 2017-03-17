package com.versawork.http.caching.files;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.Pipeline;

import com.google.gson.Gson;

/**
 * @author Sohaib
 *
 */
@Component
public class CacheAccessbyJson<T> {

	@Autowired
	protected JedisFactory jedisFactory;

	@Autowired
	private MessageSource messageSource;

	Gson gson = new Gson();

	private final static Logger LOGGER = LoggerFactory.getLogger("CACHEFILE");
	private final static Logger ETL_LOGGER = LoggerFactory.getLogger("EtlCacheLogFile");

	/**
	 * Use this method if redis is in cluster mode
	 * 
	 * @param key
	 * @param json
	 * @return
	 */

	public Boolean checkExists(String key) {

		JedisCommands jCluster = jedisFactory.getJedisConnectionFactory();
		return (jCluster.exists(key));
	}

	/**
	 * Use this method if redis is in cluster mode
	 * 
	 * @param key
	 * @param json
	 * @return
	 */

	public String fetchCacheData(String key) {

		/*
		 * Search for (key,field) pair in Redis
		 */

		try {
			JedisCommands jCluster = jedisFactory.getJedisConnectionFactory();
			// LOGGER.info("obtaining cluster connection...getting data from cache.");
			return jCluster.get(key);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(" Cache Access by Json Failed to fetch key " + key + " from cache. Message is"
					+ e.getMessage());
			return "error";
		}
	}

	/**
	 * Use this method if redis is in cluster mode
	 * 
	 * @param key
	 * @param json
	 * @return
	 */

	public Boolean setCacheData(String key, String json) {

		/*
		 * Receive a Json of the query fetched from DB for the unitNumber and
		 * put it to Redis
		 */

		try {
			Boolean isClusterOn = Boolean.parseBoolean(messageSource.getMessage("clusterMode", null,
					Locale.getDefault()));

			if (isClusterOn) {
				/*
				 * Runnable setCache = new SetCacheData(key, json,
				 * jedisFactory); Thread t1 = new Thread(setCache); t1.start();
				 */
				JedisCluster jCluster = (JedisCluster) jedisFactory.getJedisConnectionFactory();
				jCluster.set(key, json);
				jCluster.expire(key, JedisFactory.cacheTTL);
			} else {
				Jedis jedis = (Jedis) jedisFactory.getJedisConnectionFactory();
				jedis.set(key, json);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Failed to set key " + key + "  json " + json + "in cache." + e.getMessage());
			return false;

		}
	}

	// on basis of pipelining as well as cluster
	public String setCache(Map<String, List<T>> data, Type type, long transactionId) {

		try {

			Boolean isClusterOn = Boolean.parseBoolean(messageSource.getMessage("clusterMode", null,
					Locale.getDefault()));
			String key = null;
			String json = null;
			Set<Entry<String, List<T>>> set = data.entrySet();
			Iterator<Entry<String, List<T>>> iterator = set.iterator();
			Entry<String, List<T>> entry = null;

			if (isClusterOn) {
				/*
				 * Runnable setCache = null; Thread t1 = null;
				 */
				JedisCluster jCluster;
				while (iterator.hasNext()) {
					entry = iterator.next();
					key = entry.getKey();
					json = gson.toJson(entry.getValue(), type);
					ETL_LOGGER.info("[TXN ID : " + transactionId + "] KEY = " + key);
					jCluster = (JedisCluster) jedisFactory.getJedisConnectionFactory();
					jCluster.set(key, json);
					jCluster.expire(key, JedisFactory.cacheTTL);
					/*
					 * setCache = new SetCacheData(key, json, jedisFactory); t1
					 * = new Thread(setCache); t1.start();
					 */
					iterator.remove();
				}

			} else {
				Jedis jedis = (Jedis) jedisFactory.getJedisConnectionFactory();
				Pipeline pipeline = jedis.pipelined();

				while (iterator.hasNext()) {
					entry = iterator.next();
					key = entry.getKey();
					json = gson.toJson(entry.getValue(), type);
					pipeline.set(key, json);

					iterator.remove();
				}
				pipeline.sync();

			}
			return messageSource.getMessage("cache.etl.processed", null, null);
		} catch (Exception exception) {
			LOGGER.error("Error while setCache()  :" + ExceptionUtils.getStackTrace(exception));
			return messageSource.getMessage("cache.etl.unprocessed", null, null);
		}
	}

	public String getCacheData(String key) {

		/*
		 * Search for (key,field) pair in Redis
		 */
		try {
			Boolean isClusterOn = Boolean.parseBoolean(messageSource.getMessage("clusterMode", null,
					Locale.getDefault()));
			if (isClusterOn) {

				JedisCluster jCluster = (JedisCluster) jedisFactory.getJedisConnectionFactory();
				return jCluster.get(key);
			} else {
				Jedis jedis = (Jedis) jedisFactory.getJedisConnectionFactory();
				return jedis.get(key);
			}
			// LOGGER.info("obtaining cluster connection...getting data from cache.");
		} catch (Exception e) {

			LOGGER.error(" Cache Access by Json Failed to fetch key " + key + " from cache. Message is"
					+ ExceptionUtils.getStackTrace(e));
			return "error";
		}
	}

}
