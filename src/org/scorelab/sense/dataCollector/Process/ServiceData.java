package org.scorelab.sense.dataCollector.Process;

import java.util.Calendar;

import android.app.ActivityManager.RunningServiceInfo;

public class ServiceData {
	public int processId;
	public String processName;
	public String serviceClassName;
	public long timestamp;
	
	public ServiceData(RunningServiceInfo runningService) {
		processId=runningService.pid;
		processName=runningService.process;
		serviceClassName=runningService.service.getClassName();
		timestamp=Calendar.getInstance().getTimeInMillis();
	}

}
