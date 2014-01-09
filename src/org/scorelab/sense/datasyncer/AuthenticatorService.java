package org.scorelab.sense.datasyncer;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AuthenticatorService extends Service {

	private Authenticator senseAuthenticator;
	
	@Override
	public IBinder onBind(Intent intent) {
		return senseAuthenticator.getIBinder();
	}
	
	@Override
	public void onCreate() {
		senseAuthenticator = new Authenticator(this);
	}

}
