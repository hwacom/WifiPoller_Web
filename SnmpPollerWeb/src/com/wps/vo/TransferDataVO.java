package com.wps.vo;

import org.apache.commons.beanutils.BeanUtils;

import com.wps.Env;

public class TransferDataVO extends BaseVO {

	private String firstConnectTime;
	private String lastDisconnectTime;
	private String sendData;
	private String receiveData;
	private String avgSendData;
	private String avgReceiveData;

	public TransferDataVO() {
	}

	public TransferDataVO(String[] fields) {
		for (int i=0; i<fields.length; i++) {
			String fieldName = Env.FIELDS_NAME[i];

			try {
				BeanUtils.setProperty(this, fieldName, fields[i]);
			} catch (Exception e) {
				//Mapping不到欄位的不處理
			}
		}
	}

	public String getFirstConnectTime() {
		return firstConnectTime;
	}
	public void setFirstConnectTime(String firstConnectTime) {
		this.firstConnectTime = firstConnectTime;
	}
	public String getLastDisconnectTime() {
		return lastDisconnectTime;
	}
	public void setLastDisconnectTime(String lastDisconnectTime) {
		this.lastDisconnectTime = lastDisconnectTime;
	}
	public String getAvgSendData() {
		return avgSendData;
	}
	public void setAvgSendData(String avgSendData) {
		this.avgSendData = avgSendData;
	}
	public String getAvgReceiveData() {
		return avgReceiveData;
	}
	public void setAvgReceiveData(String avgReceiveData) {
		this.avgReceiveData = avgReceiveData;
	}
	public String getSendData() {
		return sendData;
	}
	public void setSendData(String sendData) {
		this.sendData = sendData;
	}
	public String getReceiveData() {
		return receiveData;
	}
	public void setReceiveData(String receiveData) {
		this.receiveData = receiveData;
	}
}
