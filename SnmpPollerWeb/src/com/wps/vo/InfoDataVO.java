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
	private int avgSendData;
	private int avgReceiveData;

	public InfoDataVO() {
	}

	public InfoDataVO(String[] fields) {
		for (int i=0; i<fields.length; i++) {
			String fieldName = Env.FIELDS_NAME[i];

			try {
				if(fields[i].indexOf("KB/分") > 0) {
					fields[i] = fields[i].replaceAll("KB/分", "");
				}
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
	public int getAvgSendData() {
		return avgSendData;
	}
	public void setAvgSendData(int avgSendData) {
		this.avgSendData = avgSendData;
	}
	public int getAvgReceiveData() {
		return avgReceiveData;
	}
	public void setAvgReceiveData(int avgReceiveData) {
		this.avgReceiveData = avgReceiveData;
	}
}
