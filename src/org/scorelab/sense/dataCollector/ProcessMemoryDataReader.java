package org.scorelab.sense.dataCollector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.scorelab.sense.util.SenseLog;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;

public class ProcessMemoryDataReader implements Runnable{
	
	private ActivityManager activityManager;
	private MemoryInfo memoryInfo;
	List<RunningAppProcessInfo> runningAppProcesses;
	
	
	public ProcessMemoryDataReader(Context context) {
		
		activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		memoryInfo = new ActivityManager.MemoryInfo();
		activityManager.getMemoryInfo(memoryInfo);
	
	}
	
	
	public void getTotalMemory(){
		
		SenseLog.d(" memoryInfo.availMem " + memoryInfo.availMem + "\n" );
		
	}
	
	public void getProcessData(){
		
		
		//SenseLog.d(" memoryInfo.totalMem " + memoryInfo. + "\n" );
		List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
		Map<Integer, String> pidMap = new HashMap<Integer, String>();
		for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses)
		{
			
		    pidMap.put(runningAppProcessInfo.pid, runningAppProcessInfo.processName);
		}
		Collection<Integer> keys = pidMap.keySet();
		ArrayList<Integer> intKeys = new ArrayList<Integer>(pidMap.keySet());
		int pids[]=new int[intKeys.size()];
		int i=0;
		for(int key : keys)
		{
			pids[i] =key;
			
			//SenseLog.d("pid is" + pids[i]);
			i++;
		}
		
		
		android.os.Debug.MemoryInfo[] memoryInfoArray = activityManager.getProcessMemoryInfo(pids);
		i=0;
		for(android.os.Debug.MemoryInfo pidMemoryInfo: memoryInfoArray){	
		    
			
			
		    	SenseLog.d(String.format("** MEMINFO in pid %d [%s] **\n",pids[i],pidMap.get(pids[i])));
		    	SenseLog.d(" pidMemoryInfo.getTotalPrivateDirty(): " + pidMemoryInfo.getTotalPrivateDirty() + "\n");
		    	SenseLog.d(" pidMemoryInfo.getTotalPss(): " + pidMemoryInfo.getTotalPss() + "\n");
		    	SenseLog.d(" pidMemoryInfo.getTotalSharedDirty(): " + pidMemoryInfo.getTotalSharedDirty() + "\n");
		    	i++;
		 }
	}
	
	
public void getServiceData(){
		
		List<RunningServiceInfo> runningServices = activityManager.getRunningServices(Integer.MAX_VALUE);
		
		for (RunningServiceInfo runningService : runningServices)
		{
			//service its runs on
		    SenseLog.d(" pid " + runningService.pid + " , processName"+runningService.process+" ,serviceName"+runningService.service.getClassName());
		}
		
	}


@Override
public void run() {
	getProcessData();
	getServiceData();
	
}
	
	
	

}
