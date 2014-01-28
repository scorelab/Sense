package org.scorelab.sense.dataCollector.battery;

import java.util.Vector;

import org.scorelab.sense.dataCollector.sms.SmsReceiverData;
import org.scorelab.sense.writer.DBWriter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

public class BatteryDataReceiver extends BroadcastReceiver {

	private Vector<BatteryData> batteryVector = new Vector<BatteryData>();
	@Override
	public void onReceive(Context context, Intent intent) {
	    
	    
	    BatteryData bt=new BatteryData(intent);
	    batteryVector.add(bt);
	    DBWriter db =new DBWriter();
		db.insertData(batteryVector);
		db.close();
		
	}

}
