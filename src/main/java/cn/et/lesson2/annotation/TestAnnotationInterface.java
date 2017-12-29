package cn.et.lesson2.annotation;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.et.lesson2.annotation.Food;


public class TestAnnotationInterface {
	public static SqlSession getSession() throws IOException{
		String resource ="cn/et/lesson2/annotation/mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//工厂类
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		return session;
	}
	
	
	@Test
	public void testAnnotationInterfaceByCustomParam() throws IOException{
		SqlSession session = getSession();
		FoodMapper fm = session.getMapper(FoodMapper.class);
		List queryFood = fm.queryFood("水煮鱼");
		System.out.println(queryFood);
		
	}
	
	@Test
	public void queryAnnotationInterfaceByCustomParam() throws IOException{
		SqlSession session = getSession();
		FoodMapper fm = session.getMapper(FoodMapper.class);
		List<Map> queryFoodByCustomParam = fm.queryFoodByCustomParam("水煮鱼");
		System.out.println(queryFoodByCustomParam);
		
	}
	
	@Test
	public void queryAnnotationInterfaceByFoodName() throws IOException{
		SqlSession session = getSession();
		FoodMapper fm = session.getMapper(FoodMapper.class);
		List<Food> queryFoodByCustomParam = fm.queryFoodByFoodName("水煮鱼");
		for(Food food:queryFoodByCustomParam){
			System.out.println(food.getFoodId()+"--------"+food.getFoodName()+"-----"+food.getFoodName());
		}
		System.out.println(queryFoodByCustomParam);
		
	}
	
	@Test
	public void deleteFoodAnnotationXmlInterface() throws IOException{
		SqlSession session = getSession();
		FoodMapper fm = session.getMapper(FoodMapper.class);
		fm.deleteFood("7");
		session.commit();
		
	}
	@Test
	public void savaFoodAnnotationXmlInterface() throws IOException{
		SqlSession session = getSession();
		FoodMapper fm = session.getMapper(FoodMapper.class);
		Food food =new Food();
		food.setFoodName("小龙虾1");
		food.setPrice("55.55");
		fm.saveFood(food);
		session.commit();
		System.out.println(food.getFoodId());
	}
}
