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
		//������
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		return session;
	}
	
	
	public static void main(String[] args) throws IOException {
		SqlSession session = getSession();
		//session�������ǣ�ָ��sql����һ��Ψһ��ʶ��
		List selectList = session.selectList("a.selectFood");
		System.out.println(selectList);
		Map selectOne = (Map)session.selectOne("a.selectFoodById");
		System.out.println(selectOne);
		
		session.update("a.updateFood");
		
		session.delete("a.deleteFood");
		
		session.commit();
	}
	/*
	 * log4j��־��Ϊ5�ּ��� 1.debug ���ԣ������׶Σ� 2.info ������Ϣ�����Ի������н׶Σ� 3.warn ������Ϣ 4.error ������Ϣ 5.fatal ϵͳ��Ϣ
	 * 
	 * ȫ�ֿ��ƻ���,�и�����root=����		 ����root=debug ����ζ�����д���debug�������ϼ���Ķ����ӡ���ļ���
	 * 	Ҫ�����ӡ����Ҫ��ȫ�ֿ��ƻ��������ñ����ߵļ��𣬱����͵ľͲ����ӡ��
	 * ��־����:fatal>error>warn>info>debug  ����ȫ�ֿ��������õļ������µ�������־������ӡ����������root=info,��info���µļ��𲻴�ӡ������failǰ���4����־�������ӡ
	 */
	//��ӡ��־
	Logger logger=Logger.getLogger(TestHelloWorld.class);
	
	/**
	 * ��Map��ֵ�ķ�ʽ
	 * @throws IOException
	 */
	@Test
	public void testQuertByMapParam() throws IOException{
		SqlSession session = getSession();
		Map map=new HashMap();
		map.put("myPrice", 50);
		map.put("myFoodName", "ˮ����");
		List<Object> selectList = session.selectList("a.selectFoodByMapParam", map);
		System.out.println(selectList);
		logger.debug(selectList);
	}
	
	/**
	 * ʹ�ö���Ĵ�ֵ��ʽ
	 * @throws IOException 
	 */
	@Test
	public void testquertByObjectParam() throws IOException{
		SqlSession session = getSession();
		Food food=new Food();
		food.setMyFoodName("ˮ����");
		food.setMyPrice("50");
		List<Object> selectList = session.selectList("a.selectFoodByObjectParam", food);
		System.out.println(selectList);
	}
	
	@Test
	public void testInsertByMapParam() throws IOException{
		SqlSession session = getSession();
		Map map=new HashMap();
		map.put("myPrice", 500);
		map.put("myFoodName", "ˮ����1");
		session.insert("a.saveFood", map);
		session.commit();
	}
}
