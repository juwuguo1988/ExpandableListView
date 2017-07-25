package com.example.administrator.expandapplication.modle;

import java.util.List;

/**
 * 一级状态实体类
 * 
 */
public class DateStatusBean {
	/* 状态名称 */
	private int statusName;
	/* 二级状态list */
	private List<TimeContentStatusBean> twoList;
	
	public int getStatusName() {
		return statusName;
	}
	public void setStatusName(int statusName) {
		this.statusName = statusName;
	}
	public List<TimeContentStatusBean> getTwoList() {
		return twoList;
	}
	public void setTwoList(List<TimeContentStatusBean> twoList) {
		this.twoList = twoList;
	}
	
	
}
