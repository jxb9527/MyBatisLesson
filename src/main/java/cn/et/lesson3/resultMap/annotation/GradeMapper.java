package cn.et.lesson3.resultMap.annotation;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface GradeMapper {
	/**
	 * 查询出所有的班级
	 * @return
	 */
	public List queryALLGrade();
	
	/**
	 * 通过id查询所有班级
	 * @param gid
	 * @return
	 */
	@Results({
		@Result(property="gname1",column="gname"),
		@Result(property="studentList",javaType=ArrayList.class,column="gid",many=@Many(select="cn.et.lesson3.resultMap.annotation.StudentMapper.queryStudentByGid"))
		})
	@Select("select * from grade where gid=#{0}")
	public Grade queryGrade(String gid);
	
	@Results({
		@Result(property="gname1",column="gname")
//		,@Result(property="gid",column="gid")
		
		})
	
	@Select("select * from grade where gid=#{0}")
	public Grade queryGradeByCradeId(String gid);
}