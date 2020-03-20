package com.hechuan.waimai.entity;

import com.hechuan.waimai.util.VeDate;

import java.util.ArrayList;
import java.util.List;

public class Cate {
	private String cateid = "C" + VeDate.getStringId();
	private String catename;
	private String addtime;
	private List<Film> flimList = new ArrayList<Film>();

	public List<Film> getFlimList() {
		return flimList;
	}

	public void setFlimList(List<Film> flimList) {
		this.flimList = flimList;
	}

	public String getCateid() {
		return cateid;
	}

	public void setCateid(String cateid) {
		this.cateid = cateid;
	}

	public String getCatename() {
		return this.catename;
	}

	public void setCatename(String catename) {
		this.catename = catename;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
}
