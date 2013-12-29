package org.scorelab.sense.dataCollector.Process;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.scorelab.sense.dataCollector.DataReader;
import org.scorelab.sense.writer.DBWriter;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;

@SuppressLint("UseSparseArrays")
public class ApplicationDataReader extends DataReader {
	
	private ActivityManager activityManager;
	
	private AppData data;
	
	Context ctx;
	
	public ApplicationDataReader(Context context) {
		ctx=context;
		activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
		activityManager.getMemoryInfo(memoryInfo);
		data=new AppData();
		data.addTotalMemData(memoryInfo);
		
	}
	
	
	private void collectProcessData(){
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
			i++;
		}
		
		
		android.os.Debug.MemoryInfo[] memoryInfoArray = activityManager.getProcessMemoryInfo(pids);
		i=0;
		for(android.os.Debug.MemoryInfo pidMemoryInfo: memoryInfoArray){	
		    
				data.addProcessData(pids[i],pidMap.get(pids[i]),pidMemoryInfo);
				i++;
		 }
		
	}
	
	
	
	
	
	public Vector<ProcessData> getProcessData(){
		
		collectProcessData();
		return data.getProcessData();
		
		
		
	}
	
	private void collectServiceData(){
		List<RunningServiceInfo> runningServices = activityManager.getRunningServices(Integer.MAX_VALUE);
		
		for (RunningServiceInfo runningService : runningServices)
		{
			data.addServiceData(runningService);
		}
		
	}
	
	
	public Vector<ServiceData> getServiceData(){
		
		collectServiceData();
		return data.getServiceData();
	}


@Override
public void run() {
	DBWriter dbW=new DBWriter(ctx);
	
	dbW.insertProcess(getProcessData());
	dbW.insertService(getServiceData());
	
	
	
}
	
	
	

}
