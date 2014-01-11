package org.scorelab.sense.dataCollector.Sensor;

import java.util.Calendar;

import org.scorelab.sense.annotation.Attributes;

import android.hardware.SensorEvent;

public class SensorData {
	@Attributes(primaryKey = true)
	public String sensorName;
	public String sensorVendor;
	
	public String sensorValues;
	public int accuracyStatus;
	@Attributes(primaryKey = true)
	public String timestamp;
	public String collectTimestamp;
	
	SensorData(SensorEvent event){
		sensorValues="";
		for(int m=0;m<event.values.length;m++){
			sensorValues+=event.values[m]+",";
			
		}
		sensorValues=sensorValues.substring(0, sensorValues.length()-1);
		accuracyStatus=event.accuracy;
		
		collectTimestamp=""+event.timestamp;
		timestamp=""+Calendar.getInstance().getTimeInMillis();
		sensorName=event.sensor.getName();
		sensorVendor=event.sensor.getVendor();
		

	}

	
	
}
