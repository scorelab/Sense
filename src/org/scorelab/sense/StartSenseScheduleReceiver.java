package org.scorelab.sense;

import org.scorelab.sense.util.SenseLog;
import org.scorelab.sense.util.SenseWakeLock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StartSenseScheduleReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		SenseWakeLock.acquireCPUWakeLoack(context);
		SenseLog.i("Sense wake up lock acquire");
		
		Intent service = new Intent(context, Sense.class);
		context.startService(service);
	}

}