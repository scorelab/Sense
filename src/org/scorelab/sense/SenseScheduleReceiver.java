package org.scorelab.sense;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SenseScheduleReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		prefs.edit().putLong("time", Calendar.getInstance().getTimeInMillis()).commit();
		
		
		AlarmManager service = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		Intent i = new Intent(context, Sense.class);
		PendingIntent pending = PendingIntent.getService(context, 0, i,0);

		Calendar cal = Calendar.getInstance();
		
		cal.add(Calendar.SECOND, 30);
		service.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(), 20 * 1000, pending);
	}

}
