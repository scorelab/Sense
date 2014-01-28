package org.scorelab.sense.dataCollector.Location;

import java.util.Calendar;

import org.scorelab.sense.annotation.Attributes;

public class LocationData {
	public double latitude; // latitude
	public double longitude; // longitude
	@Attributes(primaryKey = true)
    public String TimeStamp;
	
    LocationData(double lat,double longit){
    	latitude=lat;
    	longitude=longit;
    	TimeStamp=""+Calendar.getInstance().getTimeInMillis();
    	
    }
}
