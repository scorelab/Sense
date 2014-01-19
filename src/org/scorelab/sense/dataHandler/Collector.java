package org.scorelab.sense.dataHandler;

import org.scorelab.sense.dataCollector.DataReader;
import org.scorelab.sense.dataCollector.Process.ApplicationDataReader;
import org.scorelab.sense.dataCollector.Sensor.SensorDataReader;
import org.scorelab.sense.dataCollector.Wifi.WifiScanDataReader;
import org.scorelab.sense.dataCollector.sms.SmsReceiverDataReader;

import android.content.Context;

public class Collector {
	public enum DataType {
	    SENSOR , APP , WIFI ,SMS
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
							
				case SMS:	dr=new SmsReceiverDataReader();
				break;			
					
				default: break;
			
			}
			
			Thread DataCollector =new Thread(dr);
			DataCollector.start();
		}catch(NullPointerException e){
			
			
			
		}
	}
}
