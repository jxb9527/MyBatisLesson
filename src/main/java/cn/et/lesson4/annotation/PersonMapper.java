package cn.et.lesson4.annotation;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

public interface PersonMapper {
	/**
	 * ${}在注解情况下失效
	 * 	对象的属性取值方式： 别名.属性名
	 * 提供sql方法的参数是Map 键值对
	 * 提供sql语句方法
	 * @author Administrator
	 *
	 */
	static class PersonProvier{
		
		public String queryPersonSql(Map map){
			Person person = (Person)map.get("stu");
			String sql="select * from person";
			if(person.getPname()!=null && !"".equals(person.getPname())){
				person.setPname("%"+person.getPname()+"%");
				sql+=" and pname like #{stu.pname}";
			}
			if(person.getPage()!=null && !"".equals(person.getPage())){
				sql+=" and page like '%#{stu.page}%'";
			}
			return sql;
		}
		
		public String queryPersonSql1(Map map){
			Person person = (Person)map.get("stu");
			SQL sql = new SQL();
			sql.SELECT("*").FROM("person");
			if(person.getPname()!=null && !"".equals(person.getPname())){
				person.setPname("%"+person.getPname()+"%");
				sql.WHERE(" and pname like #{stu.pname}");
			}
			if(person.getPage()!=null && !"".equals(person.getPage())){
				sql.AND();
				sql.WHERE(" and page like #{stu.page}");
			}
			return sql.toString();
		}
	}
	
	@SelectProvider(type=PersonProvier.class,method="queryPersonSql1")
	public List<Person> queryPerson(@Param("stu") Person person);
	
	
	/**
	 * 根据性别来查所有学生
	 * 参数中如果传入sex就根据条件查，如果没有传值，就查所有男生
	 * @param sex
	 * @return
	 */
	@Select("<script>select * from person where 1=1 <choose><when test=\"sex!=null\">and psex =#{sex}</when><otherwise>and psex=1</otherwise></choose></script>")
	public List<Person> queryBySex(@Param(value="sex") Integer sex);
	
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
