package org.scorelab.sense.dataCollector.Process;


import java.util.Vector;

import org.scorelab.sense.util.SenseLog;

import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningServiceInfo;

public class AppData {
	
	private long availableMemory ;
	private Vector<ServiceData> service=new Vector<ServiceData>();
	private Vector<ProcessData> process=new Vector<ProcessData>();
	
	public void addTotalMemData(MemoryInfo memInfo){
		
		availableMemory =memInfo.availMem;
		
	}
	
	public void addProcessData(int pId,String pName , android.os.Debug.MemoryInfo memInfo){
		
			process.add(new ProcessData(pId,pName , memInfo));
			
			
			/*SenseLog.d("process Id "+pId+"  ,  process Name "+pName);
	    	SenseLog.d(" pidMemoryInfo.getTotalPrivateDirty(): " + memInfo.getTotalPrivateDirty() + "\n");
	    	SenseLog.d(" pidMemoryInfo.getTotalPss(): " + memInfo.getTotalPss() + "\n");
	    	SenseLog.d(" pidMemoryInfo.getTotalSharedDirty(): " + memInfo.getTotalSharedDirty() + "\n");*/
	}
	
	public void addServiceData(RunningServiceInfo runningService){
				
			service.add(new ServiceData(runningService));
		
			//SenseLog.d(" pid " + runningService.pid + " , processName"+runningService.process+" ,serviceName"+runningService.service.getClassName());
		
		
	}
	
	
	public Vector<ServiceData> getServiceData(){
		return service;
	}
	
	public Vector<ProcessData> getProcessData(){
		return process;
		
	}

}
