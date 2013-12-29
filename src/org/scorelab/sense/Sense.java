package org.scorelab.sense;

import java.util.Calendar;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import org.scorelab.sense.dataCollector.ProcessMemoryDataReader;
import org.scorelab.sense.dataCollector.SensorDataReader;
import org.scorelab.sense.util.SenseLog;
import org.scorelab.sense.util.SenseWakeLock;

public class Sense extends Service {

	private final IBinder senseBinder = new SenseBinder();

	@Override
	public IBinder onBind(Intent arg0) {
		return senseBinder;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		SenseLog.i("Start Sense");
		SenseLog.i("Time: " + Calendar.getInstance().getTimeInMillis() + "");

		SensorDataReader ms = new SensorDataReader(this);
		Thread sensorDataCollector = new Thread(ms);
		sensorDataCollector.start();

		ProcessMemoryDataReader psdr = new ProcessMemoryDataReader(this);
		Thread activityDataCollector = new Thread(psdr);
		activityDataCollector.start();

		// ms.getSensorData();
		SenseLog.i("End Sense");
		return Service.START_NOT_STICKY;
	}

	public class SenseBinder extends Binder {
		public Sense getService() {
			return Sense.this;
		}

	}

	@Override
	public void onDestroy() {
		SenseWakeLock.releaseCPUWakeLoack();
		SenseLog.i("Sense wake up lock release");
	}
}
