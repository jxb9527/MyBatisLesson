package cn.et.lesson4.annotation;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

public interface PersonMapper {
	/**
	 * ${}��ע�������ʧЧ
	 * 	���������ȡֵ��ʽ�� ����.������
	 * �ṩsql�����Ĳ�����Map ��ֵ��
	 * �ṩsql��䷽��
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
	 * �����Ա���������ѧ��
	 * �������������sex�͸��������飬���û�д�ֵ���Ͳ���������
	 * @param sex
	 * @return
	 */
	@Select("<script>select * from person where 1=1 <choose><when test=\"sex!=null\">and psex =#{sex}</when><otherwise>and psex=1</otherwise></choose></script>")
	public List<Person> queryBySex(@Param(value="sex") Integer sex);
	
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
