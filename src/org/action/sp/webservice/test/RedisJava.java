package org.action.sp.webservice.test;
import redis.clients.jedis.Jedis;
public class RedisJava {
   public static void main(String[] args) {
      //连接本地的 Redis 服务
      Jedis jedis = new Jedis("222.184.79.76");
//      System.out.println("Connection to server sucessfully");
      //查看服务是否运行
//      System.out.println("Server is running: "+jedis.ping());
      
      if(jedis.keys("count")==null||"".equals(jedis.keys("count"))||!jedis.exists("count")){
    	jedis.set("count", String.valueOf(new Integer(0)));
    	}
    	 Integer count = Integer.parseInt(jedis.get("count"));
    	 System.out.println(count);
 }
}