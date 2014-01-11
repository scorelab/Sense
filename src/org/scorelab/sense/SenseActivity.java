package org.scorelab.sense;



import android.app.Activity;
import android.os.Bundle;

	public class SenseActivity extends Activity{

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
			SenseScheduleReceiver s=new SenseScheduleReceiver();
			//s.abortBroadcast();
			s.clearAbortBroadcast();
			s.onReceive(this, null);
		}
	
	}
