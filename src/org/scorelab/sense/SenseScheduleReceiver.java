package org.scorelab.sense;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SenseScheduleReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		AlarmManager service = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		Intent i = new Intent(context, StartSenseScheduleReceiver.class);
		PendingIntent pending = PendingIntent.getBroadcast(context, 0, i,
				PendingIntent.FLAG_CANCEL_CURRENT);

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, 30);
		service.setInexactRepeating(AlarmManager.RTC_WAKEUP,
				cal.getTimeInMillis(), 6 * 1000, pending);
	}

}
