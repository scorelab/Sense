package org.scorelab.sense.dataCollector.sms.observe;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.scorelab.sense.util.SenseLog;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;

public class SmsObserveDataReader extends ContentObserver {

	private MessageDigest sensMessageDigest;
	Context context;

	public SmsObserveDataReader(Handler handler) {
		super(handler);
	}

	@Override
	public void onChange(boolean selfChange) {
		super.onChange(selfChange);

		try {
			sensMessageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			SenseLog.w("NoSuchAlgorithmException Throws");
		}

		Uri sMSURI = Uri.parse("content://sms/");

		Cursor cursor = context.getContentResolver().query(sMSURI, null, null,
				null, null);

		while (cursor.moveToNext()) {

			String toAddress = cursor.getString(cursor
					.getColumnIndex("address"));
			int messageLength = cursor.getString(cursor.getColumnIndex("body"))
					.length();
			long messageTimestamp = cursor.getLong(cursor
					.getColumnIndex("date"));

			sensMessageDigest.reset();
            byte[] fromAdressDigest = sensMessageDigest.digest(toAddress
                            .getBytes());
            BigInteger bigIntegerOfFromAdress = new BigInteger(1,
                            fromAdressDigest);
            String fromAddressHash = bigIntegerOfFromAdress.toString();
		}

		;

	}

}
