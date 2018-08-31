package com.wps;

import java.text.SimpleDateFormat;

public class Env {

	public static String FILE_FOLDER_PATH = "D:\\accessDB\\Wifi_Polling_Data\\";

	public final static SimpleDateFormat FORMAT_YYYYMMDD_NO_SLASH = new SimpleDateFormat("yyyyMMdd");

	public static String[] FIELDS_NAME = new String[] {
			"orderNum", "yyyymmdd", "userMacAddr", "slotId", "ipAddr", "apMacAddr", "apName", "ssidName",
			"channel", "signalStrenth", "noiseLevel", "snr",
			"firstConnectTime", "lastDisconnectTime", "sendData", "receiveData", "avgSendData", "avgReceiveData"
	};

	public static String[] INFO_FIELDS_NAME = new String[] {
			"orderNum", "userMacAddr", "ipAddr", "apName", "ssidName",
			"channel", "signalStrenth", "noiseLevel", "snr",
			"orderNum", "userMacAddr", "ipAddr", "apName", "ssidName",
			"firstConnectTime", "lastDisconnectTime", "avgSendData", "avgReceiveData"
	};

	public static String[] TRANSFER_FIELDS_NAME = new String[] {
			"orderNum", "userMacAddr", "ipAddr", "apName", "ssidName",
			"firstConnectTime", "lastDisconnectTime", "avgSendData", "avgReceiveData"
	};

	public static String FILE_CONTENT_SEPARATOR_SYMBOL = "@~";
}
