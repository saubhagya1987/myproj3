/*
 * package com.redis.testing;
 * 
 * import java.io.IOException; import java.lang.reflect.Type; import
 * java.util.HashMap; import java.util.HashSet; import java.util.List; import
 * java.util.Map.Entry; import java.util.Set;
 * 
 * import org.springframework.stereotype.Component;
 * 
 * import redis.clients.jedis.HostAndPort; import redis.clients.jedis.Jedis;
 * import redis.clients.jedis.JedisCluster; import
 * redis.clients.jedis.JedisSentinelPool; import redis.clients.jedis.Pipeline;
 * import redis.clients.jedis.Transaction; import
 * redis.clients.jedis.exceptions.JedisConnectionException;
 * 
 * import com.google.gson.Gson;
 * 
 * @Component public class TestRedisCluster<T> {
 * 
 * static JedisCluster jCluster; static Jedis jedis; public static String data =
 * "{\"responseData\":{\"result\":0,\"description\":\"Success\"},\"PatientVisitList\":[{\"patientVisitId\":2714236,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"06/16/2015\",\"attendingPhysicianName\":\"DOLEZAL, BENJAMIN\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"},{\"patientVisitId\":2714086,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"06/15/2015\",\"attendingPhysicianName\":\"DOLEZAL, BENJAMIN\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"},{\"patientVisitId\":2683900,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"04/08/2015\",\"attendingPhysicianName\":\"DOLEZAL, BENJAMIN\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"},{\"patientVisitId\":2660347,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"03/04/2015\",\"attendingPhysicianName\":\"ANDERSON, JAMES\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"},{\"patientVisitId\":2658909,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"03/02/2015\",\"attendingPhysicianName\":\"DOLEZAL, BENJAMIN\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"},{\"patientVisitId\":2444016,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"01/26/2015\",\"attendingPhysicianName\":\"EDEN, ANDREA\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"},{\"patientVisitId\":2444015,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"11/25/2014\",\"attendingPhysicianName\":\"ANDERSON, JAMES\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"},{\"patientVisitId\":2444014,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"11/11/2014\",\"attendingPhysicianName\":\"DOLEZAL, BENJAMIN\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"},{\"patientVisitId\":2444013,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"10/24/2014\",\"attendingPhysicianName\":\"DOLEZAL, BENJAMIN\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"},{\"patientVisitId\":2444012,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"10/22/2014\",\"attendingPhysicianName\":\"DOLEZAL, BENJAMIN\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"},{\"patientVisitId\":2444011,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"10/20/2014\",\"attendingPhysicianName\":\"ANDERSON, JAMES\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"},{\"patientVisitId\":2444006,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"10/07/2014\",\"attendingPhysicianName\":\"DOLEZAL, BENJAMIN\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"},{\"patientVisitId\":2444010,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"10/06/2014\",\"attendingPhysicianName\":\"SWISHER, KELSEY\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"},{\"patientVisitId\":2444009,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"09/03/2014\",\"attendingPhysicianName\":\"DOLEZAL, BENJAMIN\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"},{\"patientVisitId\":2444008,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"08/28/2014\",\"attendingPhysicianName\":\"ANDERSON, JAMES\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"},{\"patientVisitId\":2444007,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"06/08/2014\",\"attendingPhysicianName\":\"PEARSON, TERESA\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"},{\"patientVisitId\":2717827,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"06/08/2014\",\"attendingPhysicianName\":\"PEARSON, TERESA\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"},{\"patientVisitId\":2443999,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"04/07/2014\",\"attendingPhysicianName\":\"DOLEZAL, BENJAMIN\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"},{\"patientVisitId\":2444005,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"03/24/2014\",\"attendingPhysicianName\":\"DOLEZAL, BENJAMIN\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"},{\"patientVisitId\":2444004,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"02/17/2014\",\"attendingPhysicianName\":\"ANDERSON, JAMES\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"},{\"patientVisitId\":2444003,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"01/21/2014\",\"attendingPhysicianName\":\"BROWN, MIRANDA\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"},{\"patientVisitId\":2444002,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"01/07/2014\",\"attendingPhysicianName\":\"ANDERSON, JAMES\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"},{\"patientVisitId\":2444001,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"12/16/2013\",\"attendingPhysicianName\":\"DOLEZAL, BENJAMIN\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"},{\"patientVisitId\":2444000,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"12/16/2013\",\"attendingPhysicianName\":\"ANDERSON, JAMES\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"},{\"patientVisitId\":2443998,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"10/08/2013\",\"attendingPhysicianName\":\"DOLEZAL, BENJAMIN\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"},{\"patientVisitId\":2443997,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"08/22/2013\",\"attendingPhysicianName\":\"ANDERSON, JAMES\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"},{\"patientVisitId\":2443996,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"08/20/2013\",\"attendingPhysicianName\":\"DOLEZAL, BENJAMIN\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"},{\"patientVisitId\":2717826,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"07/05/2013\",\"attendingPhysicianName\":\"SWISHER, KELSEY\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"},{\"patientVisitId\":2443995,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"03/29/2013\",\"attendingPhysicianName\":\"DOLEZAL, BENJAMIN\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"},{\"patientVisitId\":2443994,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"01/04/2013\",\"attendingPhysicianName\":\"DOLEZAL, BENJAMIN\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"},{\"patientVisitId\":2443993,\"clientId\":0,\"clientDatabaseId\":0,\"accountId\":2316,\"visitTypeId\":1,\"visitDate\":\"10/03/2012\",\"attendingPhysicianName\":\"DOLEZAL, BENJAMIN\",\"visitType\":\"inpatient\",\"unitNumber\":\"ML00019216\"}]}"
 * ; public static Boolean cachingOn = true;
 * 
 * public static Boolean mailCounter = true;
 * 
 * public static Integer cacheTTL;
 * 
 * static Gson gson = new Gson();
 * 
 * public static void main(String args[]) {
 * 
 * HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();
 * for(int i=1;i<=100;i++){ List<String> list = new ArrayList<String>();
 * list.add("test"); list.add("Hello"); hashMap.put(String.valueOf(i), list); }
 * TestRedisCluster<String> obj = new TestRedisCluster<String>(); Type type =
 * new TypeToken<List<String>>() {}.getType(); obj.setCache(hashMap,type);
 * 
 * 
 * // testPipeline(); // testJedis(); // testMulti(); // testCluster();
 * testSentinel(); // Type type = new TypeToken<List<String>>() {}.getType(); }
 * 
 * public static void testSentinel() { final Set<String> sentinels = new
 * HashSet<String>(); sentinels.add("192.168.1.147:7007");
 * sentinels.add("192.168.1.147:7008"); sentinels.add("192.168.1.147:7009");
 * JedisSentinelPool jedisSentinelPool = new JedisSentinelPool("mymaster",
 * sentinels); Jedis jedis = jedisSentinelPool.getResource(); Pipeline p = null;
 * try { p = jedis.pipelined(); int x = 0; long startSimple =
 * System.currentTimeMillis();
 * 
 * while (x < 1000000) {
 * 
 * if(p==null){ jedis = new Jedis("192.168.1.147", 7004); p = jedis.pipelined();
 * }
 * 
 * try { p.set("key_" + x, data); } catch (JedisConnectionException
 * connectionException) { jedis = jedisSentinelPool.getResource(); p =
 * jedis.pipelined(); p.set("key_" + x, data); } x++; }
 * System.out.println("Object Sent to Pipline :" + x); p.sync(); p.close();
 * jedis.close();
 * 
 * for(Object obj:list){ System.out.println(obj.toString()); }
 * 
 * long endSimple = System.currentTimeMillis(); long simpleDiff = endSimple -
 * startSimple; x = 0; System.out.println(" Simple transactions: " + simpleDiff
 * / 1000.0 + " seconds"); } catch (Exception e) { e.printStackTrace();
 * cachingOn = false; mailCounter = false; } finally { try { p.close(); } catch
 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
 * jedisSentinelPool.close(); }
 * 
 * }
 * 
 * public static void testPipeline() {
 * 
 * Jedis jedis = new Jedis("192.168.1.147", 7004); try { Pipeline p =
 * jedis.pipelined(); int x = 0; long startSimple = System.currentTimeMillis();
 * 
 * while (x < 1000000) {
 * 
 * if(p==null){ jedis = new Jedis("192.168.1.147", 7004); p = jedis.pipelined();
 * }
 * 
 * p.set("key_" + x, data); x++; } System.out.println("Object Sent to Pipline :"
 * + x); p.sync(); p.close(); jedis.close();
 * 
 * for(Object obj:list){ System.out.println(obj.toString()); }
 * 
 * long endSimple = System.currentTimeMillis(); long simpleDiff = endSimple -
 * startSimple; x = 0; System.out.println(" Simple transactions: " + simpleDiff
 * / 1000.0 + " seconds"); } catch (Exception e) { e.printStackTrace();
 * cachingOn = false; mailCounter = false; } }
 * 
 * public static void testJedis() {
 * 
 * Jedis jedis = new Jedis("192.168.1.147", 7000); try { int x = 0; long
 * startSimple = System.currentTimeMillis(); while (x < 1000000) {
 * jedis.set("key_" + x, "TEST_KEY_" + x); x++; } long endSimple =
 * System.currentTimeMillis(); long simpleDiff = endSimple - startSimple; x = 0;
 * System.out.println(" Simple transactions: " + simpleDiff / 1000.0 +
 * " seconds"); } catch (Exception e) { e.printStackTrace(); cachingOn = false;
 * mailCounter = false; } finally { jedis.close(); } }
 * 
 * public static void testCluster() {
 * 
 * Set<HostAndPort> hashAndPort = new HashSet<HostAndPort>(); long startSimple =
 * System.currentTimeMillis(); if (jCluster == null) { try {
 * 
 * 
 * Jedis j = new Jedis("192.168.1.22", 7001); System.out.println("got a node");
 * System.out.println(j.get("test"));
 * 
 * cacheTTL = 10000; HostAndPort hnp = null;
 * 
 * int port = 7000;
 * 
 * String hostname1 = "172.16.1.71"; String hostname2 = "172.16.1.12";
 * 
 * String hostName = "192.168.1.147";
 * 
 * hnp = new HostAndPort(hostName, port); hashAndPort.add(hnp); port = 7001; hnp
 * = new HostAndPort(hostName, port); hashAndPort.add(hnp); port = 7002; hnp =
 * new HostAndPort(hostName, port); hashAndPort.add(hnp); port = 7003; hnp = new
 * HostAndPort(hostName, port); hashAndPort.add(hnp); jCluster = new
 * JedisCluster(hashAndPort); int x = 0; while (x < 1000000) { //
 * System.out.println("SET KEY_________"+"key_"+x+" : "+jCluster.get("key_"+x));
 * jCluster.set("key_" + x, data); x++; } long endSimple =
 * System.currentTimeMillis(); long simpleDiff = endSimple - startSimple;
 * System.out.println(" Simple transactions: " + simpleDiff / 1000.0 +
 * " seconds"); x = 0;
 * 
 * while(x<100) { System.out.println("key_"+x+" : "+jCluster.get("key_"+x));
 * x++; }
 * 
 * } catch (Exception e) { e.printStackTrace(); cachingOn = false; mailCounter =
 * false; } }
 * 
 * }
 * 
 * public boolean setCache(HashMap<String, List<T>> data, Type type) { try {
 * Boolean isClusterOn = false; if (isClusterOn) {
 * 
 * for (Entry<String, List<Object>> entry : data.entrySet()) { String key =
 * entry.getKey(); String json = gson.toJson(entry.getValue(), type); Runnable
 * setCache = new SetCacheData(key,json, jedisFactory ); Thread t1 = new
 * Thread(setCache); t1.start(); }
 * 
 * return true; } else { Jedis jedis = jedisConnectionFactory(); Pipeline
 * pipeline = jedis.pipelined(); for (Entry<String, List<T>> entry :
 * data.entrySet()) { String key = entry.getKey(); String json =
 * gson.toJson(entry.getValue(), type); pipeline.set(key, json); }
 * pipeline.sync(); return true; } } catch (Exception exception) {
 * exception.printStackTrace(); return false; } }
 * 
 * public static Jedis jedisConnectionFactory() { if (jedis == null) { Integer
 * port = 7000; String host = "192.168.1.147"; jedis = new Jedis(host, port); }
 * return jedis; }
 * 
 * public static void testMulti() { long startSimple =
 * System.currentTimeMillis(); Jedis jedis = new Jedis("192.168.1.147", 7000);
 * 
 * Transaction transaction = jedis.multi();
 * 
 * // jedis.multi(); int x = 0;
 * 
 * while (x < 100) { System.out.println("key_" + x); transaction.set("key_" + x,
 * "TEST_KEY_" + x); // jedis.sadd("key_"+x, "TEST_KEY_"+x); x++; } List<Object>
 * txResult = transaction.exec(); long endSimple = System.currentTimeMillis();
 * long simpleDiff = endSimple - startSimple;
 * System.out.println(" Simple transactions: " + simpleDiff + " milli seconds");
 * 
 * x = 0; while(x<100) {
 * System.out.println("key_"+x+" : "+transaction.get("key_"+x)); x++; }
 * 
 * 
 * } }
 */