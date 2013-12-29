package org.scorelab.sense.util;

import android.content.Context;
import android.os.PowerManager;

public class SenseWakeLock {

	private static PowerManager.WakeLock cPUWakeLoack;

	public static void acquireCPUWakeLoack(Context context) {

		SenseLog.i("Acquire CPU wakeup lock start");
		
		if (cPUWakeLoack == null) {
			
			SenseLog.i("CPU wakeUp log is not null");
			
			PowerManager pm = (PowerManager) context
					.getSystemService(Context.POWER_SERVICE);
			cPUWakeLoack = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
					"SenseWakeLock");
		}

		cPUWakeLoack.acquire();
		SenseLog.i("Acquire CPU wakeup lock stop");

	}

	public static void releaseCPUWakeLoack() {

		SenseLog.i("Release CPU wakeup lock start");
		
		if (cPUWakeLoack != null) {
			
			SenseLog.i("CPU wakeUp log is null");
			
			cPUWakeLoack.release();
			cPUWakeLoack = null;
		}
		
		SenseLog.i("Release CPU wakeup log end");
	
	}

}
