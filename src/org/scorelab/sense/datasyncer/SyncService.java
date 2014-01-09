package org.scorelab.sense.datasyncer;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SyncService extends Service {

	private static SyncAdapter senseSyncAdapter = null;
	private static final Object senseSyncAdapterLock = new Object();

	@Override
	public void onCreate() {

		synchronized (senseSyncAdapterLock) {
			if (senseSyncAdapter == null) {
				senseSyncAdapter = new SyncAdapter(getApplicationContext(),
						true);
			}
		}
	}

	@Override
	public IBinder onBind(Intent intent) {

		return senseSyncAdapter.getSyncAdapterBinder();
	}

}
