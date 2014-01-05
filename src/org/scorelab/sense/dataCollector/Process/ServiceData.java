package org.scorelab.sense.dataCollector.Process;

import java.util.Calendar;

import org.scorelab.sense.annotation.Attributes;

import android.app.ActivityManager.RunningServiceInfo;

public class ServiceData {
	@Attributes(primaryKey = true)
	public int processId;
	public String processName;
	@Attributes(primaryKey = true)
	public String serviceClassName;
	@Attributes(primaryKey = true)
	public String timestamp;
	
	public ServiceData(RunningServiceInfo runningService) {
		processId=runningService.pid;
		processName=runningService.process;
		serviceClassName=runningService.service.getClassName();
		timestamp=""+Calendar.getInstance().getTimeInMillis();
	}
	
	

}
