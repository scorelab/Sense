package org.scorelab.sense.dataCollector;

import android.hardware.SensorEvent;

public class SensorData {
	
	public float sensorValues[];
	public int accuracyStatus;
	public long timestamp;
	
	SensorData(SensorEvent event){
		sensorValues=event.values;
		accuracyStatus=event.accuracy;
		timestamp=event.timestamp;
		

	}

}
