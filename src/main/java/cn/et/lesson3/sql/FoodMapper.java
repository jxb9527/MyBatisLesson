package cn.et.lesson3.sql;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.et.lesson2.xml.Food;

public interface FoodMapper {
	/**
	 * �������Ʋ�ѯ���в�Ʒ��Ϣ
	 * @param foodName
	 * @return
	 */
	public List queryFood(@Param(value="foodName") String foodName);
	
}
