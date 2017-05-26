package com.example.administrator.myapplication.modle;

import java.io.Serializable;
import org.json.JSONObject;

public class RecordBean extends BaseBean<RecordBean>implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;// 服药记录id;
	private String medicineId;// 药品id;
	private String medicineName;// 药品名称
	private String planId;// 服药计划id
	private long takeTime;// 服药时间
	private int count;// 服药片数
	private int dosage;// 服药剂量 毫克数
	private int status;// 服药记录结果：1为已服，2为未付
	private int source;// 服药记录来源：1服务器生成，2来源药盒,3为用户app操作
	private long confirmedAt;// 确认时间
	private int reminder;// 服药提醒时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(String medicineId) {
		this.medicineId = medicineId;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public long getTakeTime() {
		return takeTime;
	}

	public void setTakeTime(long takeTime) {
		this.takeTime = takeTime;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getDosage() {
		return dosage;
	}

	public void setDosage(int dosage) {
		this.dosage = dosage;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public long getConfirmedAt() {
		return confirmedAt;
	}

	public void setConfirmedAt(long confirmedAt) {
		this.confirmedAt = confirmedAt;
	}

	public int getReminder() {
		return reminder;
	}

	public void setReminder(int reminder) {
		this.reminder = reminder;
	}


	@Override
	public RecordBean parseJSON(JSONObject jsonObj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject toJSON() {
		// TODO Auto-generated method stub
		return null;
	}

}
