package org.scorelab.sense.dataCollector.sms;

import org.apache.http.cookie.SM;
import org.scorelab.sense.Sense;
import org.scorelab.sense.dataCollector.DataReader;
import org.scorelab.sense.dataCollector.Sensor.SensorData;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import org.scorelab.sense.util.SenseLog;
import org.scorelab.sense.writer.DBWriter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.telephony.SmsMessage;

public class SmsReceiverDataReader extends DataReader{
	Context context;
	private Vector<SmsReceiverData> smsVector = new Vector<SmsReceiverData>();
	public SmsReceiverDataReader() {
		context=Sense.context;
	}
	
	void collectSmsData(){
		/*Uri uri = Uri.parse("content://sms/inbox");
	       Cursor c= context.getContentResolver().query(uri, null, null ,null,null);
	       startManagingCursor(c);
	        
	       // Read the sms data and store it in the list
	       if(c.moveToFirst()) {
	           for(int i=0; i < c.getCount(); i++) {
	              
	              sms.setBody(c.getString(c.getColumnIndexOrThrow("body")).toString());
	               sms.setNumber(c.getString(c.getColumnIndexOrThrow("address")).toString());
	               smsList.add(sms);
	                
	               c.moveToNext();
	           }
	       }
	       c.close();
	*/
		
		
	}
	public Vector<SmsReceiverData> getSmsData(){
		
		collectSmsData();
		return smsVector;
		
		
		
		
	}
	

	
	@Override
	public void run() {
		SenseLog.d("running -sms");
		
		DBWriter dbW=new DBWriter();
		dbW.insertData(getSmsData());
		dbW.close();
		
	}
	
}
