package com.wps.vo;

public class BaseVO {

	private String orderNum;
	private String yyyymmdd;
	private String userMacAddr;
	private String slotId;
	private String ipAddr;
	private String apMacAddr;
	private String apName;
	private String ssidName;

	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getYyyymmdd() {
		return yyyymmdd;
	}
	public void setYyyymmdd(String yyyymmdd) {
		this.yyyymmdd = yyyymmdd;
	}
	public String getUserMacAddr() {
		return userMacAddr;
	}
	public void setUserMacAddr(String userMacAddr) {
		this.userMacAddr = userMacAddr;
	}
	public String getSlotId() {
		return slotId;
	}
	public void setSlotId(String slotId) {
		this.slotId = slotId;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public String getApMacAddr() {
		return apMacAddr;
	}
	public void setApMacAddr(String apMacAddr) {
		this.apMacAddr = apMacAddr;
	}
	public String getApName() {
		return apName;
	}
	public void setApName(String apName) {
		this.apName = apName;
	}
	public String getSsidName() {
		return ssidName;
	}
	public void setSsidName(String ssidName) {
		this.ssidName = ssidName;
	}
}
