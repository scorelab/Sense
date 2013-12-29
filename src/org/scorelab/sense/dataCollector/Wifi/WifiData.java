package org.scorelab.sense.dataCollector.Wifi;

import java.util.Calendar;

import android.net.wifi.ScanResult;

public class WifiData {

	public String SSID;
	public String BSSID;
	public String Capability;
	public long TimeStamp;
	public WifiData(ScanResult wifiSpot) {
		// TODO Auto-generated constructor stub
		
		SSID=wifiSpot.SSID;
		BSSID=wifiSpot.BSSID;
		Capability=wifiSpot.capabilities;
		TimeStamp =Calendar.getInstance().getTimeInMillis();
	}
	

}
