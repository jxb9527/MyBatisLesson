package cn.et.lesson2.xml;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface FoodMapper {
	/**
	 * �������Ʋ�ѯ���в�Ʒ��Ϣ
	 * @param foodName
	 * @return
	 */
	public List queryFood(String foodName);
	
	
	/**
	 * �������Ʋ�ѯ���в�Ʒ��Ϣ
	 * @param foodName
	 * @return
	 */
	public List queryFoodByFoodName(@Param("foodName") String foodName);
	
	/**
	 * ����idɾ����Ʒ
	 * @param foodId
	 */
	public void deleteFood(String foodId);
	
	
	public void saveFood(Food food);
}
