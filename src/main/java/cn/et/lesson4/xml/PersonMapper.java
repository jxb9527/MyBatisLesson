package cn.et.lesson4.xml;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PersonMapper {
	
	
	public List<Person> queryPerson(Person person);
	
	
	/**
	 * �����Ա���������ѧ��
	 * �������������sex�͸��������飬���û�д�ֵ���Ͳ���������
	 * @param sex
	 * @return
	 */
	public List<Person> queryBySex(@Param(value = "sex") Integer sex);
	
	/**
	 * �޸�ѧ����Ϣ
	 * @param person
	 */
	public void updatePerson(Person person);
	
	/**
	 * ͨ������İ༶��ѯ���е�ѧ��
	 * @param ageList
	 */
	public List<Person> queryPersonByAnyAge(@Param("ageList") List<Integer> ageList);
}
