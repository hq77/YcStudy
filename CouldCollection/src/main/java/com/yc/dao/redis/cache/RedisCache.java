package com.yc.dao.redis.cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;

public class RedisCache implements Cache {
	/*
	 * 日志对象
	 */
	private static Logger logger=LogManager.getLogger(RedisCache.class);
	/*
	 * 对象的编号
	 */
	private String id;
	/*
	 * 同步锁
	 */
	private ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
	
	private Jedis redisClient=creatRedis();
	public RedisCache(String id){
		if(id==null){
			throw new IllegalArgumentException("Cache instance requires an Id");
		}
		logger.debug("create an cache instance with id:" + id);
		this.id=id;
	}
	/*
	 * 获取当前那这个缓存的id
	 * @see org.apache.ibatis.cache.Cache#getId()
	 */
	public String getId() {
		return this.id;
	}
	/*
	 * 从连接池中取
	 */
	public static  Jedis creatRedis() {
		Jedis jedis=RedisPool.getPool().getResource();
		return jedis;
	}
	/*
	 * 
	 */
	public void putObject(Object key, Object value) {
		byte[] keybyte=SerializeableUtil.seralize(key);
		byte[] valuebyte=SerializeableUtil.seralize(value);
		this.redisClient.set(keybyte, valuebyte);
	}

	public Object getObject(Object key) {
		//缓存穿透.
		byte[] values=this.redisClient.get(  SerializeableUtil.seralize(key)       );
		//System.out.println(   values );
		if(  values==null  ){
			//this.putObject(  SerializableUtil.serialize(key)  , null);
			return null;
		}
		Object obj=SerializeableUtil.unSerialize(  values);
		return obj;
	}
	

	public Object removeObject(Object key) {
		byte[] keybyte= SerializeableUtil.seralize(key);
		return this.redisClient.expire(keybyte, 0);
	}

	public void clear() {
		this.redisClient.flushDB();    
	}

	public int getSize() {
		Long size=this.redisClient.dbSize();
		int s=Integer.valueOf(  size+"");
		return s;
	}

	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}

}
