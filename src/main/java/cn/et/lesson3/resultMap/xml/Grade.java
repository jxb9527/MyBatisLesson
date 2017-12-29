package cn.et.lesson3.resultMap.xml;

import java.util.ArrayList;
import java.util.List;

/**
 * 属性名必须和数据库的列名的一致，不考虑大小写
 * 如果属性名和数据库列名不一致相应的xxxMapper.xml中配置结果集映射
 * @author Administrator
 *
 */
public class Grade {
	private Integer gid;
	private String gname1;
	private List<Student> studentList = new ArrayList<Student>();

	public Integer getGid() {
		return gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public String getGname1() {
		return gname1;
	}

	public void setGname1(String gname1) {
		this.gname1 = gname1;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

}
