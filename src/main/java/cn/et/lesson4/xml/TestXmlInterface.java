package cn.et.lesson4.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;


public class TestXmlInterface {
	public static SqlSession getSession() throws IOException{
		String resource ="cn/et/lesson4/xml/mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//π§≥ß¿‡
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		return session;
	}
	@Test
	public void queryAllPerson() throws IOException{
		SqlSession session = getSession();
		PersonMapper pm = session.getMapper(PersonMapper.class);
		Person person = new Person();
		List<Person> queryPerson = pm.queryPerson(person);
		for(Person p:queryPerson){
			System.out.println(p.getPname());
		}
	}
	
	
	@Test
	public void queryPerson() throws IOException{
		SqlSession session = getSession();
		PersonMapper pm = session.getMapper(PersonMapper.class);
		List<Person> queryBySex = pm.queryBySex(0);
		for(Person p:queryBySex){
			System.out.println(p.getPname());
		}
	}
	
	@Test
	public void updatePerson() throws IOException{
		SqlSession session = getSession();
		PersonMapper pm = session.getMapper(PersonMapper.class);
		Person person = new Person();
		person.setPid(3);
		pm.updatePerson(person);
		session.commit();
	}
	
	@Test
	public void foreachPerson() throws IOException{
		SqlSession session = getSession();
		PersonMapper pm = session.getMapper(PersonMapper.class);
		List list=new ArrayList();
		list.add(12);
		list.add(17);
		List<Person> queryPersonByAnyAge = pm.queryPersonByAnyAge(list);
		for(Person p:queryPersonByAnyAge){
			System.out.println(p.getPname());
		}
	}
}