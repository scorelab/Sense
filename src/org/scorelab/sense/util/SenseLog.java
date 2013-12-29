package org.scorelab.sense.util;

import android.util.Log;

public class SenseLog {

	private static final String SENSE_DEBUG_LOG_TAG = "DEBUG";
	private static final String SENSE_ERROR_LOG_TAG = "ERROR";
	private static final String SENSE_INFO_LOG_TAG = "INFO";
	private static final String SENSE_VERBOSE_LOG_TAG = "VERBOSE";
	private static final String SENSE_WARN_LOG_TAG = "WARN";

	public static void d(String msg) {
		
		
		
		Log.d(SENSE_DEBUG_LOG_TAG, msg);
	}

	public static void e(String msg) {
		Log.e(SENSE_ERROR_LOG_TAG, msg);
	}

	public static void i(String msg) {
		Log.i(SENSE_INFO_LOG_TAG, msg);
	}

	public static void v(String msg) {
		Log.v(SENSE_VERBOSE_LOG_TAG, msg);
	}

	public static void w(String msg) {
		Log.v(SENSE_WARN_LOG_TAG, msg);
	}

}
