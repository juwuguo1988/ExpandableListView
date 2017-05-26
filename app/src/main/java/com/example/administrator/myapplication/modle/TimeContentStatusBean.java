package com.example.administrator.myapplication.modle;

import java.util.List;

/**
 * 二级级状态实体类
 * 
 * 
 */
public class TimeContentStatusBean {
	/* 状态名称 */
	private List<RecordBean> mRecordBeans;
	/* 活动时间 */
	private int actionTime;

	public List<RecordBean> getmRecordBeans() {
		return mRecordBeans;
	}

	public void setmRecordBeans(List<RecordBean> mRecordBeans) {
		this.mRecordBeans = mRecordBeans;
	}

	public int getActionTime() {
		return actionTime;
	}

	public void setActionTime(int actionTime) {
		this.actionTime = actionTime;
	}

}
