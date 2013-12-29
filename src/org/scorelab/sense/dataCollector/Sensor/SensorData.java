package org.scorelab.sense.dataCollector.Sensor;

import java.util.Calendar;

import android.hardware.SensorEvent;

public class SensorData {
	
	public String sensorName;
	public String sensorVendor;
	
	public float sensorValues[];
	public int accuracyStatus;
	public long timestamp;
	public long collectTimestamp;
	SensorData(SensorEvent event){
		sensorValues=event.values;
		accuracyStatus=event.accuracy;
		
		collectTimestamp=event.timestamp;
		timestamp=Calendar.getInstance().getTimeInMillis();
		sensorName=event.sensor.getName();
		sensorVendor=event.sensor.getVendor();
		

	}

}
