package org.scorelab.sense.dataCollector.Wifi;

import java.util.List;
import java.util.Vector;

import org.scorelab.sense.Sense;
import org.scorelab.sense.dataCollector.DataReader;
import org.scorelab.sense.util.SenseLog;
import org.scorelab.sense.writer.DBWriter;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

public class WifiScanDataReader extends DataReader{
	private WifiManager wifiManager;
	private boolean wifiState=false;
	private Vector<WifiData> wifiInfo=new Vector<WifiData>();
	Context ctx;
	public WifiScanDataReader() {
		
		Context context=Sense.context;
		ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		if (mWifi.isAvailable()) {
			
			wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
			
			wifiState=wifiManager.isWifiEnabled();
			
			if(wifiState){
				
				wifiManager.startScan();
				
				
			}else{
				SenseLog.d("WifiNotEnable");
				
				
			}
		}else{
			SenseLog.d("WifiNotAvailable");
			
			
		}
		
		
		
	}
	
	private void collectWifiData(){
		
		if(!wifiState){
			return;
		}
		
		
		
		
		List<ScanResult> wifiList = wifiManager.getScanResults();
		
		
		for (ScanResult wifiSpot : wifiList)
		{
			
			
		
			wifiInfo.add(new WifiData(wifiSpot));
		    SenseLog.d("SSID "+wifiSpot.SSID + "wifi capabilities"+wifiSpot.capabilities);
		}
		
	}
	
public Vector<WifiData> getWifiData(){
		collectWifiData();
	
		return wifiInfo;
		
	}

@Override
public void run() {
	
	DBWriter dbW=new DBWriter();
	dbW.insertData(getWifiData());
	dbW.close();
	
	
}


}
