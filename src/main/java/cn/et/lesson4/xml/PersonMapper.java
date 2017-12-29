package cn.et.lesson4.xml;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PersonMapper {
	
	
	public List<Person> queryPerson(Person person);
	
	
	/**
	 * 根据性别来查所有学生
	 * 参数中如果传入sex就根据条件查，如果没有传值，就查所有男生
	 * @param sex
	 * @return
	 */
	public List<Person> queryBySex(@Param(value = "sex") Integer sex);
	
	/**
	 * 修改学生信息
	 * @param person
	 */
	public void updatePerson(Person person);
	
	/**
	 * 通过传入的班级查询所有的学生
	 * @param ageList
	 */
	public List<Person> queryPersonByAnyAge(@Param("ageList") List<Integer> ageList);
}
