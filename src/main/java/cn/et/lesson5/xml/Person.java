package cn.et.lesson5.xml;

import java.io.Serializable;

public class Person implements Serializable{
	private Integer pid;
	private String pname;
	private Integer page;
	private Integer psex;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPsex() {
		return psex;
	}

	public void setPsex(Integer psex) {
		this.psex = psex;
	}

}
