package cn.et.lesson3.resultMap.xml;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface StudentMapper {
	/**
	 * 根据名称查询所有菜品信息
	 * @param foodName
	 * @return
	 */
	public List queryFood(@Param(value="foodName") String foodName);
	
	/**
	 * 通过编号查询学生
	 * @param sid
	 * @return
	 */
	public Student queryStudent(String sid);
}
