package org.scorelab.sense.dataCollector.Wifi;

import java.util.Calendar;

import org.scorelab.sense.annotation.Attributes;

import android.net.wifi.ScanResult;

public class WifiData {
	@Attributes(primaryKey = true)
	public String SSID;
	public String BSSID;
	public String Capability;
	@Attributes(primaryKey = true)
	public String TimeStamp;
	public WifiData(ScanResult wifiSpot) {
		// TODO Auto-generated constructor stub
		
		SSID=wifiSpot.SSID;
		BSSID=wifiSpot.BSSID;
		Capability=wifiSpot.capabilities;
		TimeStamp =""+Calendar.getInstance().getTimeInMillis();
	}
	

}
