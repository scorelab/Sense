package org.scorelab.sense.dataCollector.Process;

import java.util.Calendar;

import org.scorelab.sense.annotation.Attributes;



public class ProcessData {
	@Attributes(primaryKey = true)
	public int processId;
	public String processName;
	public int totalPrivateDirty;
	public int totalPss;
	public int totalSharedDirty;
	@Attributes(primaryKey = true)
	public String timestamp;
	

	public ProcessData(int pId, String pName,android.os.Debug.MemoryInfo memInfo) {
		processId=pId;
		processName=pName;
		
		totalPrivateDirty=memInfo.getTotalPrivateDirty();
		totalPss=memInfo.getTotalPss();
		totalSharedDirty=memInfo.getTotalSharedDirty();
		timestamp=""+Calendar.getInstance().getTimeInMillis();
		
	}


	
}
