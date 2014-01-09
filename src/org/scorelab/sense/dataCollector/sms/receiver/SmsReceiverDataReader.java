package org.scorelab.sense.dataCollector.sms.receiver;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.scorelab.sense.util.SenseLog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SmsReceiverDataReader extends BroadcastReceiver {

	private MessageDigest sensMessageDigest;
	Context context;

	@Override
	public void onReceive(Context context, Intent intent) {

		this.context = context;
		try {
			sensMessageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			SenseLog.w("NoSuchAlgorithmException Throws");
		}

		Bundle bundle = intent.getExtras();
		Object messages[] = (Object[]) bundle.get("pdus");

		for (Object object : messages) {

			SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) object);

			String fromAddress = smsMessage.getDisplayOriginatingAddress()
					.substring(1);
			int messageLength = smsMessage.getMessageBody().length();
			long messageTimestamp = smsMessage.getTimestampMillis();

			sensMessageDigest.reset();
			byte[] fromAdressDigest = sensMessageDigest.digest(fromAddress
					.getBytes());
			BigInteger bigIntegerOfFromAdress = new BigInteger(1,
					fromAdressDigest);
			String fromAddressHash = bigIntegerOfFromAdress.toString();
		}

	}

}
