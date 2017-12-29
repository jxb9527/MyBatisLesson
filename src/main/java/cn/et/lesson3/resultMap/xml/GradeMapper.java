package cn.et.lesson3.resultMap.xml;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	public Grade queryGrade(String gid);
	
}
