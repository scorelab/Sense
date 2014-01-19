package org.scorelab.sense.dataCollector.sms;
import java.util.Calendar;

import org.scorelab.sense.annotation.Attributes;

public class SmsReceiverData {
	@Attributes(primaryKey = true)
	public String fromAddress;
	public int messageLength;
	public String messageTimestamp;
	@Attributes(primaryKey = true)
	public String timestamp;
	//public String collectTimestamp;
	
	public SmsReceiverData(String fromAddress, int messageLength, long messageTimestamp) {
		this.fromAddress=fromAddress;
		this.messageLength=messageLength;
		this.messageTimestamp=""+messageTimestamp;
		timestamp=""+Calendar.getInstance().getTimeInMillis();
	}
}




