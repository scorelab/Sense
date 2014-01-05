package org.scorelab.sense.dataCollector.Sensor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.TimeUnit;


import org.scorelab.sense.dataCollector.DataReader;
import org.scorelab.sense.util.SenseLog;
import org.scorelab.sense.writer.DBWriter;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;




public class SensorDataReader extends DataReader implements SensorEventListener {
	private SensorManager mSensorManager;
	private List<Sensor> mSensors;
	private Map<String, SensorData> senseDataStruct = new HashMap<String, SensorData>();
	private Vector<SensorData> sensorVector = new Vector<SensorData>();
	Context ctx;
	
	
	
	public SensorDataReader(Context ctx) {
		this.ctx=ctx;
		mSensorManager = (SensorManager) ctx.getSystemService(Context.SENSOR_SERVICE);
		mSensors= mSensorManager.getSensorList(Sensor.TYPE_ALL);
		
		
		for (Sensor sensor : mSensors)
		{
			
			mSensorManager.registerListener((SensorEventListener) this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
		}
		
		
	}
	
	private void collectSensorData(){
		SenseLog.i("Sensor Type");
		for (Sensor sensor : mSensors)
		{
			try{
			
			
			if (senseDataStruct.containsKey(sensor.getName())){ 
				
				SensorData sensorInfo=senseDataStruct.get(sensor.getName());
				
				sensorVector.add(sensorInfo);
				
				//DEBUG
				
				SenseLog.d("Sensor Name "+sensor.getName()+", Type "+ sensor.getType() + " , sensorValue :" + sensorInfo.sensorValues);
				//SenseLog.d("Sensor Name "+sensor.getName()+", Type "+ sensor.getType());
				//SenseLog.d(sensor.getType() + " , sensorValue :" + Values);    	
				  
			}
			}catch(Throwable e){
				
				
				SenseLog.d("error on sensors");
				
			}
				
		}
		mSensorManager.unregisterListener(this);
		
		
	}
	
	public Vector<SensorData> getSensorData(){
		try {
			TimeUnit.MILLISECONDS.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		collectSensorData();
		return sensorVector;
		
		
		
		
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
		SenseLog.d("running -sensor");
		Vector<SensorData> v=getSensorData();
		DBWriter dbW=new DBWriter(ctx);
		SenseLog.d("db -sensor");
		dbW.insertSensor(v);
		
		
	}
	

}
