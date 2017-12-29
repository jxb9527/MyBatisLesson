package cn.et.lesson2.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;


public class TestXmlInterface {
	public static SqlSession getSession() throws IOException{
		String resource ="cn/et/lesson2/xml/mybatis.xml";
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
	
	@Test
	public void testXmlInterfaceQueryFoodName() throws IOException{
		SqlSession session = getSession();
		FoodMapper fm = session.getMapper(FoodMapper.class);
		List queryFood = fm.queryFoodByFoodName("水煮鱼");
		System.out.println(queryFood);
		
	}
	
	
	@Test
	public void testXmlInterfaceSaveFood() throws IOException{
		SqlSession session = getSession();
		FoodMapper fm = session.getMapper(FoodMapper.class);
		Food food =new Food();
		food.setFoodName("小龙虾");
		food.setPrice("55");
		fm.saveFood(food);
		session.commit();
		System.out.println(food.getFoodId());
		
	}
	
	
	@Test
	public void deleteXmlInterface() throws IOException{
		SqlSession session = getSession();
		FoodMapper fm = session.getMapper(FoodMapper.class);
		fm.deleteFood("7");
		session.commit();
		
	}
}
