package cn.et.lesson3.resultMap.annotation;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface StudentMapper {
	/**
	 * 通过编号查询学生
	 * @param sid
	 * @return
	 */
	@Results({
		@Result(column="gid",property="grade",one=@One(select="cn.et.lesson3.resultMap.annotation.queryGradeByCradeId"))
		
	})
	@Select("select * from student where sid=#{0}")
	public Student queryStudent(String sid);
	
	@Select("select * from student where gid=#{0}")
	public List<Student> queryStudentByGid(String gid);
}
