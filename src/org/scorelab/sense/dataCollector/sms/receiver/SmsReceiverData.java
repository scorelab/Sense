package org.scorelab.sense.dataCollector.sms.receiver;

import org.scorelab.sense.annotation.Attributes;

public class SmsReceiverData {
	@Attributes(primaryKey = true)
	public String fromAddress;
	public String messageLength;
	public String messageTimestamp;
	@Attributes(primaryKey = true)
	public String timestamp;
	public String collectTimestamp;
	
	public SmsReceiverData(String fromAddress, String messageLength, String messageTimestamp) {
		
		
	}
}
