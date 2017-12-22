package com.feed.util;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.feed.ecp.common.constants.Constants;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MemcachedUtils {
	protected static MemCachedClient mcc = new MemCachedClient();

	static {
		String[] servers = { Constants.memcachedServer };

		Integer[] weights = { Integer.valueOf(3) };

		SockIOPool pool = SockIOPool.getInstance();

		pool.setServers(servers);
		pool.setWeights(weights);

		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaxIdle(21600000L);

		pool.setMaintSleep(30L);

		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setSocketConnectTO(0);

		pool.initialize();
	}

	public MemCachedClient getMemcached() {
		return mcc;
	}

	public static void examples() {
		mcc.set("test", "This is a test String");
		String bar = (String) mcc.get("test");
		System.out.println(bar);
	}
	/**
	 * 获取缓存里所有的key
	 * @param memCachedClient
	 * @return
	 */
	public static List<String> getAllKey(MemCachedClient memCachedClient) {
		List<String> keylist = new ArrayList<String>();
		Map<String, Map<String, String>> statsItems = memCachedClient.statsItems(); // 获取所有items
		Map<String, String> statsItems_sub = null;
		String server = Constants.memcachedServer;
		String statsItems_sub_key = null;
		int items_number = 0;
		Map<String, Map<String, String>> statsCacheDump = null;
		Map<String, String> statsCacheDump_sub = null;
		String statsCacheDumpsub_key = null;
		for (Iterator<String> iterator = statsItems.keySet().iterator(); iterator.hasNext();) {
			server = iterator.next(); // server地址
			keylist.add(server);
			statsItems_sub = statsItems.get(server);
			for (Iterator<String> iterator_item = statsItems_sub.keySet().iterator(); iterator_item.hasNext();) {
				statsItems_sub_key = iterator_item.next();
				if (statsItems_sub_key.toUpperCase().startsWith("items:".toUpperCase())
						&& statsItems_sub_key.toUpperCase().endsWith(":number".toUpperCase())) {
					items_number = Integer.parseInt(statsItems_sub.get(statsItems_sub_key).trim());
					statsCacheDump = memCachedClient.statsCacheDump(new String[] { server }, Integer.parseInt(statsItems_sub_key.split(":")[1].trim()), items_number); // 获取一个item
					for (Iterator<String> statsCacheDump_iterator = statsCacheDump.keySet().iterator(); statsCacheDump_iterator.hasNext();) {
						statsCacheDump_sub = statsCacheDump.get(statsCacheDump_iterator.next());
						for (Iterator<String> iterator_keys = statsCacheDump_sub.keySet().iterator(); iterator_keys.hasNext();) {
							statsCacheDumpsub_key = (String) iterator_keys.next(); // 获取item的key
							keylist.add(statsCacheDumpsub_key);
						}
					}
				}
			}
		}
		return keylist;
	}

	public static void main(String[] args) {
		examples();
		mcc.set("aa", "ddd");
		System.out.println(mcc.get("aa"));
//		List<String> list=getAllKey(mcc);
//		Map<String, List<?>> map=new HashMap<String, List<?>>();
//		for (String string : list) {
//			List<?> lists=(List<?>) mcc.get(string);
//			map.put(string, lists);
//		}
	}
}