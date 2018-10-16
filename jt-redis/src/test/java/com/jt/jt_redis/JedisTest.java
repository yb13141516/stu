package com.jt.jt_redis;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class JedisTest {
	@Test	//完成单实例链接
	public void jedis(){
		Jedis jedis = new Jedis("192.168.225.203", 7000);
		//jedis.auth("123456");
		jedis.set("name", "tony");	//调用redis命令set
		String s = jedis.get("name");
		System.out.println(s);
		jedis.close();
	}
	
	//redis测试分片
		@Test
		public void test02(){
			//2.创建分片的连接池
			JedisPoolConfig poolConfig = new JedisPoolConfig();
			poolConfig.setMaxTotal(500);
			poolConfig.setMaxIdle(20);
			//3.准备redis的分片
			List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
			shards.add(new JedisShardInfo("192.168.225.203", 7000));
			shards.add(new JedisShardInfo("192.168.225.203", 7001));
			shards.add(new JedisShardInfo("192.168.225.203", 7002));
			//1.创建分片的对象
			ShardedJedisPool jedisPool = 
					new ShardedJedisPool(poolConfig, shards);
			//获取jedis对象
			ShardedJedis shardedJedis = jedisPool.getResource();
			//5.redis的存取值操作
			for (int i = 0; i < 9; i++) {
				shardedJedis.set("n"+i,"我是分片操作"+i);
			}
		}

}
