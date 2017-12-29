package cn.et.lesson5.xml;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;


public class TestXmlInterface {
	
	public static SqlSessionFactory getSessionFactory() throws IOException{
		String resource ="cn/et/lesson5/xml/mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}
	
	
	public static SqlSession getSession() throws IOException{
		String resource ="cn/et/lesson5/xml/mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//工厂类
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		return session;
	}
	
	/**
	 * 一级缓存：同一个session对象针对同一份数据的查询产生的缓存。
	 * 	第一次查询时，调用数据，获取数据后通过session设置一级缓存中，第二次查询时，通过session一级缓存判断是否存在相同主键的数据值，如果存在直接返回引用，否则查询数据库
	 * @throws IOException
	 */
	@Test
	public void queryAllPerson() throws IOException{
		SqlSession session = getSession();
		PersonMapper pm = session.getMapper(PersonMapper.class);
		
		Person p1 = pm.queryPersonById(1);
		
		//从缓存中查的 一级缓存
		Person p2= pm.queryPersonById(1);
		
		System.out.println(p1==p2);
	}
	
	/**
	 * 二级缓存 同一个sessionFactory下的不同session，可以共享数据
	 * @throws IOException
	 */
	@Test
	public void querySecondPerson() throws IOException{
		SqlSessionFactory sessionFactory = getSessionFactory();
		SqlSession session1 = sessionFactory.openSession();
		SqlSession session2 = sessionFactory.openSession();
		
		PersonMapper pm1 = session1.getMapper(PersonMapper.class);
		
		PersonMapper pm2 = session2.getMapper(PersonMapper.class);
		
		Person p1 = pm1.queryPersonById(1);
		
		session1.close();
		
		Person p2= pm2.queryPersonById(1);
		
		System.out.println(p1==p2);
	}
	/**
	 * mybatis有两级缓存
//	 * 一级缓存：是内嵌的缓存，是不允许更改的，是基于session的级别缓存，同一个session共享的缓存
	 * 二级缓存：同一个sessionFactory的，是可插拔的，是可以定义的，默认可以通过总的开发，如果你的namespace，想用缓存，就可以是cache标签，默认是使用fifo缓存策略
	 * 
	 */
}