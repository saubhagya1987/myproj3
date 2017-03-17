package com.versawork.http.caching.files;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;

/**
 * @author Sohaib
 *
 */
@Component
public class JedisFactory {

	@Autowired
	private MessageSource messageSource;

	private static JedisCluster jCluster;
	private static Jedis jedis;

	static Logger LOGGER = LoggerFactory.getLogger("CACHEFILE");

	public static Boolean cachingOn = true;

	public static Boolean mailCounter = true;

	public static Integer cacheTTL;
	private static AbstractApplicationContext applicationContext = null;
	private static JedisCommands command;

	public JedisCommands getJedisConnectionFactory() {

		if (command == null)
			synchronized (this) {

				if (command == null) {
					Boolean isClusterOn = Boolean.parseBoolean(messageSource.getMessage("clusterMode", null,
							Locale.getDefault()));

					if (isClusterOn) {
						command = JedisConFactory();
					} else {
						command = jedisConnectionFactory();
					}
				}
			}
		return command;
	}

	private JedisCluster JedisConFactory() {

		Set<HostAndPort> hashAndPort = new HashSet<HostAndPort>();

		if (jCluster == null) {

			try {

				Integer totalSlots = Integer
						.parseInt(messageSource.getMessage("totalSlots", null, Locale.getDefault()));
				cacheTTL = Integer.parseInt(messageSource.getMessage("cacheTTL", null, Locale.getDefault()));
				HostAndPort hnp = null;

				applicationContext = new ClassPathXmlApplicationContext("classpath:jedis-config.xml");

				for (Integer x = 0; x < totalSlots; x++) {
					String beanId = messageSource.getMessage(x.toString(), null, Locale.getDefault());
					JedisConnectionFactory jedisConnectionFactory = applicationContext.getBean(beanId,
							JedisConnectionFactory.class);
					int port = jedisConnectionFactory.getPort();
					String hostname = jedisConnectionFactory.getHostName();

					hnp = new HostAndPort(hostname, port);
					hashAndPort.add(hnp);
				}
				jCluster = new JedisCluster(hashAndPort);
				jCluster.get("test");
				LOGGER.info("Successfully initialized Cluster");
				cachingOn = true;
				return jCluster;
			} catch (Exception e) {
				cachingOn = false;
				mailCounter = false;
				e.printStackTrace();
				LOGGER.error("Failed to initialize cluster. " + e.getMessage());
			}

		}
		return jCluster;
	}

	private Jedis jedisConnectionFactory() {

		if (jCluster == null) {
			try {
				Integer port = Integer.parseInt(messageSource.getMessage("port", null, Locale.getDefault()));
				String host = messageSource.getMessage("hostName", null, Locale.getDefault());
				jedis = new Jedis(host, port);
				cachingOn = true;
				cacheTTL = Integer.parseInt(messageSource.getMessage("cacheTTL", null, Locale.getDefault()));
			} catch (Exception e) {
				mailCounter = false;
				cachingOn = false;
			}
		}

		return jedis;
	}

	/*
	 * public void shutdown() { if(applicationContext != null)
	 * applicationContext.close(); applicationContext =null; }
	 */
}
