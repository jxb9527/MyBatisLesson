package cn.et.lesson5.xml;

import java.io.IOException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;

import redis.clients.jedis.Jedis;

public class RedisCach implements Cache {
	
	Jedis jedis=new Jedis("localhost",6379);
	/**
	 * �����ID ���������ռ�
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
	 * mybatis��ȡ����ʱ �����ݿ��еĶ�ȡ�����ݣ�ͨ��putObject���õ�������
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
	 * mybatis���Զ�����getObject����Ƿ񻺴��д���
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
	 * mybatis������Ի��Զ��ж��ڴ�Ĵ�С�������Ƿ�ɾ��ĳЩ���ھ�Զ������
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
	 * �������
	 */
	@Override
	public void clear() {
//		jedis.flushDB();
	}

	/**
	 * ���ػ���ĸ���
	 */
	@Override
	public int getSize() {
		return 0;
	}

	/**
	 * ����
	 */
	@Override
	public ReadWriteLock getReadWriteLock() {
		return new ReentrantReadWriteLock();
	}

}
