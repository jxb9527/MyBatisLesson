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
		//������
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		return session;
	}
	
	/**
	 * һ�����棺ͬһ��session�������ͬһ�����ݵĲ�ѯ�����Ļ��档
	 * 	��һ�β�ѯʱ���������ݣ���ȡ���ݺ�ͨ��session����һ�������У��ڶ��β�ѯʱ��ͨ��sessionһ�������ж��Ƿ������ͬ����������ֵ���������ֱ�ӷ������ã������ѯ���ݿ�
	 * @throws IOException
	 */
	@Test
	public void queryAllPerson() throws IOException{
		SqlSession session = getSession();
		PersonMapper pm = session.getMapper(PersonMapper.class);
		
		Person p1 = pm.queryPersonById(1);
		
		//�ӻ����в�� һ������
		Person p2= pm.queryPersonById(1);
		
		System.out.println(p1==p2);
	}
	
	/**
	 * �������� ͬһ��sessionFactory�µĲ�ͬsession�����Թ�������
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
	 * mybatis����������
//	 * һ�����棺����Ƕ�Ļ��棬�ǲ�������ĵģ��ǻ���session�ļ��𻺴棬ͬһ��session����Ļ���
	 * �������棺ͬһ��sessionFactory�ģ��ǿɲ�εģ��ǿ��Զ���ģ�Ĭ�Ͽ���ͨ���ܵĿ�����������namespace�����û��棬�Ϳ�����cache��ǩ��Ĭ����ʹ��fifo�������
	 * 
	 */
}