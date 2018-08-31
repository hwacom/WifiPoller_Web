package com.wps.vo;

import org.apache.commons.beanutils.BeanUtils;

import com.wps.Env;

public class InfoDataVO extends BaseVO {

	private String channel;
	private String signalStrenth;
	private String noiseLevel;
	private String snr;
	private String firstConnectTime;
	private String lastDisconnectTime;
	private String sendData;
	private String receiveData;
	private String avgSendData;
	private String avgReceiveData;

	public InfoDataVO() {
	}

	public InfoDataVO(String[] fields) {
		for (int i=0; i<fields.length; i++) {
			String fieldName = Env.FIELDS_NAME[i];

			try {
				BeanUtils.setProperty(this, fieldName, fields[i]);
			} catch (Exception e) {
				//Mapping不到欄位的不處理
			}
		}
	}

	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getSignalStrenth() {
		return signalStrenth;
	}
	public void setSignalStrenth(String signalStrenth) {
		this.signalStrenth = signalStrenth;
	}
	public String getNoiseLevel() {
		return noiseLevel;
	}
	public void setNoiseLevel(String noiseLevel) {
		this.noiseLevel = noiseLevel;
	}
	public String getSnr() {
		return snr;
	}
	public void setSnr(String snr) {
		this.snr = snr;
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
}
