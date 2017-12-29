package cn.et.lesson2.xml;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface FoodMapper {
	/**
	 * 根据名称查询所有菜品信息
	 * @param foodName
	 * @return
	 */
	public List queryFood(String foodName);
	
	
	/**
	 * 根据名称查询所有菜品信息
	 * @param foodName
	 * @return
	 */
	public List queryFoodByFoodName(@Param("foodName") String foodName);
	
	/**
	 * 根据id删除菜品
	 * @param foodId
	 */
	public void deleteFood(String foodId);
	
	
	public void saveFood(Food food);
}
