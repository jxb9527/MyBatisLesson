package cn.et.lesson3.resultMap.xml;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface GradeMapper {
	/**
	 * ��ѯ�����еİ༶
	 * @return
	 */
	public List queryALLGrade();
	
	/**
	 * ͨ��id��ѯ���а༶
	 * @param gid
	 * @return
	 */
	public Grade queryGrade(String gid);
	
}
