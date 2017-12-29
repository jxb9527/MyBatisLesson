package cn.et.lesson5.xml;

import java.io.IOException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;

import redis.clients.jedis.Jedis;

public class RedisCach implements Cache {
	
	Jedis jedis=new Jedis("localhost",6379);
	/**
	 * 缓存的ID 就是命名空间
	 */
	private String cacheId;

	public RedisCach(String cacheId) {
		this.cacheId=cacheId;
	}

	@Override
	public String getId() {
		return null;
	}

	/**
	 * mybatis读取数据时 将数据库中的读取的数据，通过putObject设置到缓存中
	 */
	@Override
	public void putObject(Object key, Object value) {
		try {
			jedis.set(JavaRedis.objectToByteArray(key), JavaRedis.objectToByteArray(value));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * mybatis会自动调用getObject检测是否缓存中存在
	 */
	@Override
	public Object getObject(Object key) {
		try {
			byte[] bt=jedis.get(JavaRedis.objectToByteArray(key));
			if(bt==null){
				return null;
			}
			return JavaRedis.byteArrayToObject(bt);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * mybatis缓存策略会自动判断内存的大小，决定是否删除某些过期久远的数据
	 */
	@Override
	public Object removeObject(Object key) {
		Object obj=getObject(key);
		try {
			jedis.del(JavaRedis.objectToByteArray(key));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * 清除缓存
	 */
	@Override
	public void clear() {
//		jedis.flushDB();
	}

	/**
	 * 返回缓存的个数
	 */
	@Override
	public int getSize() {
		return 0;
	}

	/**
	 * 加锁
	 */
	@Override
	public ReadWriteLock getReadWriteLock() {
		return new ReentrantReadWriteLock();
	}

}
