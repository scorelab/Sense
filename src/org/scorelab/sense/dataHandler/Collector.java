package org.scorelab.sense.dataHandler;

import org.scorelab.sense.dataCollector.DataReader;
import org.scorelab.sense.dataCollector.Process.ApplicationDataReader;
import org.scorelab.sense.dataCollector.Sensor.SensorDataReader;
import org.scorelab.sense.dataCollector.Wifi.WifiScanDataReader;

import android.content.Context;

public class Collector {
	public enum DataType {
	    SENSOR , APP , WIFI 
	};
	
	public Collector(Context ctx,DataType dt) {
		try{
			DataReader dr=null;
			
			switch(dt){
				case SENSOR: dr=new SensorDataReader();
							 break;
					
				case APP: 	dr=new ApplicationDataReader();
							break;
					
				case WIFI:	dr=new WifiScanDataReader();
							break;
					
				default: break;
			
			}
			
			Thread DataCollector =new Thread(dr);
			DataCollector.start();
		}catch(NullPointerException e){
			
			
			
		}
	}
}
