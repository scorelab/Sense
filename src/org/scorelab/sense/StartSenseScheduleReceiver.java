package org.scorelab.sense;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StartSenseScheduleReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent service = new Intent(context, Sense.class);
		context.startService(service);
	}

}
