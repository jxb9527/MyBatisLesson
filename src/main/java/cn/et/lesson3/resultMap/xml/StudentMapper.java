package cn.et.lesson3.resultMap.xml;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface StudentMapper {
	/**
	 * �������Ʋ�ѯ���в�Ʒ��Ϣ
	 * @param foodName
	 * @return
	 */
	public List queryFood(@Param(value="foodName") String foodName);
	
	/**
	 * ͨ����Ų�ѯѧ��
	 * @param sid
	 * @return
	 */
	public Student queryStudent(String sid);
}
