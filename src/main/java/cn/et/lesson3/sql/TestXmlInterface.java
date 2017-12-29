package cn.et.lesson3.sql;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.et.lesson2.xml.Food;


public class TestXmlInterface {
	public static SqlSession getSession() throws IOException{
		String resource ="cn/et/lesson3/sql/mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//工厂类
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		return session;
	}
	
	
	@Test
	public void testXmlInterface() throws IOException{
		SqlSession session = getSession();
		FoodMapper fm = session.getMapper(FoodMapper.class);
		List queryFood = fm.queryFood("水煮鱼");
		System.out.println(queryFood);
		
	}
}