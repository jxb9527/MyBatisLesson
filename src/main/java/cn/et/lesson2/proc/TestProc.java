package cn.et.lesson2.proc;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class TestProc {
	public static SqlSession getSession() throws IOException{
		String resource ="cn/et/lesson2/proc/mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//π§≥ß¿‡
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		return session;
	}
	
	@Test
	public void testProc() throws IOException{
		SqlSession session = getSession();
		Map map = new HashMap();
		map.put("p1", 123);
		map.put("p2", 234);
		session.selectOne("proc.call_prg_add",map);
		System.out.println(map.get("result"));
	}
	
	@Test
	public void testFun() throws IOException{
		SqlSession session = getSession();
		Map map = new HashMap();
		map.put("p1", 123);
		map.put("p2", 234);
		session.selectOne("proc.call_fun_add",map);
		System.out.println(map.get("result"));
	}
}
