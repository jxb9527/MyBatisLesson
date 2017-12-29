package cn.et.lesson1;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;


public class TestHelloWorld {
	public static SqlSession getSession() throws IOException{
		String resource ="cn/et/lesson1/mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//工厂类
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		return session;
	}
	
	
	public static void main(String[] args) throws IOException {
		SqlSession session = getSession();
		//session操作的是，指向sql语句的一个唯一标识符
		List selectList = session.selectList("a.selectFood");
		System.out.println(selectList);
		Map selectOne = (Map)session.selectOne("a.selectFoodById");
		System.out.println(selectOne);
		
		session.update("a.updateFood");
		
		session.delete("a.deleteFood");
		
		session.commit();
	}
	/*
	 * log4j日志分为5种级别 1.debug 调试（开发阶段） 2.info 运行信息（测试或者运行阶段） 3.warn 警告消息 4.error 错误消息 5.fatal 系统消息
	 * 
	 * 全局控制机制,有个变量root=级别		 例：root=debug （意味着所有带有debug级别及以上级别的都会打印到文件）
	 * 	要不想打印，就要在全局控制机制中设置比他高的级别，比他低的就不会打印。
	 * 日志级别:fatal>error>warn>info>debug  所有全局控制中设置的级别以下的所有日志都不打印，比如设置root=info,则info的下的级别不打印，设置fail前面的4个日志都不会打印
	 */
	//打印日志
	Logger logger=Logger.getLogger(TestHelloWorld.class);
	
	/**
	 * 用Map传值的方式
	 * @throws IOException
	 */
	@Test
	public void testQuertByMapParam() throws IOException{
		SqlSession session = getSession();
		Map map=new HashMap();
		map.put("myPrice", 50);
		map.put("myFoodName", "水煮鱼");
		List<Object> selectList = session.selectList("a.selectFoodByMapParam", map);
		System.out.println(selectList);
		logger.debug(selectList);
	}
	
	/**
	 * 使用对象的传值方式
	 * @throws IOException 
	 */
	@Test
	public void testquertByObjectParam() throws IOException{
		SqlSession session = getSession();
		Food food=new Food();
		food.setMyFoodName("水煮鱼");
		food.setMyPrice("50");
		List<Object> selectList = session.selectList("a.selectFoodByObjectParam", food);
		System.out.println(selectList);
	}
	
	@Test
	public void testInsertByMapParam() throws IOException{
		SqlSession session = getSession();
		Map map=new HashMap();
		map.put("myPrice", 500);
		map.put("myFoodName", "水煮鱼1");
		session.insert("a.saveFood", map);
		session.commit();
	}
}
