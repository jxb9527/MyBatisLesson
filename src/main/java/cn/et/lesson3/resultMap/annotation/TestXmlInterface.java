package cn.et.lesson3.resultMap.annotation;

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
		String resource ="cn/et/lesson3/resultMap/annotation/mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//工厂类
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		return session;
	}
	
	@Test
	public void queryAllGrade() throws IOException{
		SqlSession session = getSession();
		GradeMapper mapper = session.getMapper(GradeMapper.class);
		List<Grade> queryALLGrade = mapper.queryALLGrade();
		for(Grade g:queryALLGrade){
			System.out.println(g.getGid()+"--------"+g.getGname1());
		}
	}
	
	//多对一
	@Test
	public void testManyToOne() throws IOException{
		SqlSession session = getSession();
		StudentMapper gm = session.getMapper(StudentMapper.class);
		Student student = gm.queryStudent("3");
		System.out.println(student.getSid()+"-------"+student.getSname()+"------"+student.getGrade().getGname1());
	}
	
	
	//一对多
	@Test
	public void testOneToMany() throws IOException{
		SqlSession session = getSession();
		GradeMapper gm = session.getMapper(GradeMapper.class);
		Grade grade = gm.queryGrade("3");
		for(Student s:grade.getStudentList()){
			System.out.println(s.getSid()+"-----"+s.getSname());
		}
	}
}