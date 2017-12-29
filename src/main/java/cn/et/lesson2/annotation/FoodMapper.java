package cn.et.lesson2.annotation;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

public interface FoodMapper {
	/**
	 * 根据名称查询所有菜品信息
	 * @param foodName
	 * @return
	 */
	@Select("select * from food where foodname=#{0}")
	public List<Map> queryFood(String foodName);
	
	/*
	 * 
	 * @Param("foodName")指定自己定制的参数名
	 * 
	 */
	@Select("select * from food where foodname=#{foodName}")
	public List<Map> queryFoodByCustomParam(@Param("foodName") String foodName);
	
	
	
	/*
	 * 
	 * @Param("foodName")指定自己定制的参数名
	 * 
	 */
	@Select("select * from food where foodname like '%${foodName}%'")
	public List<Food> queryFoodByFoodName(@Param("foodName") String foodName);
	
	
	/**
	 * 根据id删除菜品
	 * @param foodId
	 */
	@Delete("delete from food where foodid=#{0}")
	public void deleteFood(String foodId);
	
	/**
	 * 新增菜品
	 * @param foodId
	 */
	@SelectKey(before=true,keyProperty="foodId",resultType=int.class,statement="select Food_Sec.Nextval from dual")
	@Insert("insert into food values(#{foodId},#{foodName},#{price})")
	public void saveFood(Food food);
}
