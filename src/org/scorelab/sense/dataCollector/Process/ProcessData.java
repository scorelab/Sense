package org.scorelab.sense.dataCollector.Process;

import java.util.Calendar;



public class ProcessData {
	public int processId;
	public String processName;
	public int totalPrivateDirty;
	public int totalPss;
	public int totalSharedDirty;
	public long timestamp;
	

	public ProcessData(int pId, String pName,android.os.Debug.MemoryInfo memInfo) {
		processId=pId;
		processName=pName;
		
		totalPrivateDirty=memInfo.getTotalPrivateDirty();
		totalPss=memInfo.getTotalPss();
		totalSharedDirty=memInfo.getTotalSharedDirty();
		timestamp=Calendar.getInstance().getTimeInMillis();
		
	}


	
}
