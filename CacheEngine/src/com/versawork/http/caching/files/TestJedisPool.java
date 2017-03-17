package com.versawork.http.caching.files;

import java.util.HashSet;
import java.util.Set;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class TestJedisPool {
	public static void main(String[] args) {
		Set<HostAndPort> hashAndPort = new HashSet<HostAndPort>();
		String hostname = "192.168.1.8";
		HostAndPort hnp = new HostAndPort(hostname, 7000);
		hashAndPort.add(hnp);
		hnp = new HostAndPort(hostname, 7001);
		hashAndPort.add(hnp);
		hnp = new HostAndPort(hostname, 7002);
		hashAndPort.add(hnp);
		hnp = new HostAndPort(hostname, 7003);
		hashAndPort.add(hnp);
		hnp = new HostAndPort(hostname, 7004);
		hashAndPort.add(hnp);
		hnp = new HostAndPort(hostname, 7005);
		hashAndPort.add(hnp);
		// long startTime = System.currentTimeMillis();
		JedisCluster jCluster = new JedisCluster(hashAndPort);
		try {
			jCluster.get("test12");
			// System.out.println("here done");
		} catch (Exception e) {
			// System.out.println("here "+e.getMessage());
		}
		// System.out.println(jCluster.echo("abc"));
		// long endTime = System.currentTimeMillis();
		// System.out.println(endTime - startTime);
	}
}