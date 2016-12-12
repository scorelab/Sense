package org.scorelab.sense.dataCollector.sms.observe;

import org.scorelab.sense.annotation.Attributes;

public class SmsObserveData {
	@Attributes(primaryKey = true)
    public String toAddress;
    public String messageLength;
    public String messageTimestamp;
    @Attributes(primaryKey = true)
    public String timestamp;
    public String collectTimestamp;
    
    public SmsObserveData(String toAddress, String messageLength, String messageTimestamp){
    	
    }
}
