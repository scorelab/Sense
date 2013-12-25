package org.scorelab.sense.dataCollector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.scorelab.sense.util.SenseLog;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;




public class SensorDataReader implements SensorEventListener ,Runnable{
	private SensorManager mSensorManager;
	private List<Sensor> mSensors;
	private Map<String, SensorData> senseDataStruct = new HashMap<String, SensorData>();
	
	
	public SensorDataReader(Context ctx) {
		
		
		
		mSensorManager = (SensorManager) ctx.getSystemService(Context.SENSOR_SERVICE);
		mSensors= mSensorManager.getSensorList(Sensor.TYPE_ALL);
		
		
		for (Sensor sensor : mSensors)
		{
			
			mSensorManager.registerListener((SensorEventListener) this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
		}
		
		
	}
	
	public void getSensorData(){
		
		
		SenseLog.i("Sensor Type");
		for (Sensor sensor : mSensors)
		{
			try{
			
			
			if (senseDataStruct.containsKey(sensor.getName())){ 
				String Values="";
				SensorData sensorInfo=senseDataStruct.get(sensor.getName());
				for(int i=0;i<sensorInfo.sensorValues.length;i++){
					Values+=sensorInfo.sensorValues[i]+" , ";
					
				}
			
			//SenseLog.d("Sensor Name "+sensor.getName()+", Type "+ sensor.getType() + " , sensorValue :" + Values);
				SenseLog.d("Sensor Name "+sensor.getName()+", Type "+ sensor.getType());
				SenseLog.d(sensor.getType() + " , sensorValue :" + Values);
			}
			}catch(Throwable e){
				
				
				SenseLog.d("Sensor Name "+sensor.getName()+", Type "+ sensor.getType());
				
			}
				
		}
		mSensorManager.unregisterListener(this);
		
		
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
		
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
			//SenseLog.d("SENSOR READING "+event.sensor.getName());
		
		
			 senseDataStruct.put(event.sensor.getName(), new SensorData(event));
			
		 
		
		
	}

	@Override
	public void run() {
		//Thread.sleep(10);
		getSensorData();
		
	}
	

}
