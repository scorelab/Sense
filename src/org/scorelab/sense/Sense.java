package org.scorelab.sense;

import java.util.Calendar;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;


import org.scorelab.sense.dataHandler.Collector;
import org.scorelab.sense.util.SenseLog;

public class Sense extends Service {

	private final IBinder senseBinder = new SenseBinder();
	
	
	
	@Override
	public IBinder onBind(Intent arg0) {
		return senseBinder;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		SenseLog.i("Start Sense");
		SenseLog.i("Time: "
				+ Calendar.getInstance().getTimeInMillis() + "");
		
		
		
		
		
		//Collector Sensor=new Collector(this,Collector.DataType.SENSOR);
		Collector App=new Collector(this,Collector.DataType.APP);
		//Collector Wifi=new Collector(this,Collector.DataType.WIFI);
		
		//ms.getSensorData();
		SenseLog.i("End Sense");
		return Service.START_NOT_STICKY;
	}

	public class SenseBinder extends Binder {
		public Sense getService() {
			return Sense.this;
		}

	}
}
