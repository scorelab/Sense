package org.scorelab.sense.dataCollector.battery;

import java.util.Calendar;

import org.scorelab.sense.annotation.Attributes;

import android.content.Intent;
import android.os.BatteryManager;

public class BatteryData {
	
	public int health;
	public int level;
    public int plugged;
    public int present;
    public int scale;
    public int status;
    public String technology;
    public int temperature;
    public int voltage;
    @Attributes(primaryKey = true)
    public String timestamp;
    
	BatteryData( Intent intent){
		timestamp=""+Calendar.getInstance().getTimeInMillis();
		health= intent.getIntExtra(BatteryManager.EXTRA_HEALTH,0);
	    level= intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
	    plugged= intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,0);
	    present= intent.getExtras().getBoolean(BatteryManager.EXTRA_PRESENT)?1:0;
	    scale= intent.getIntExtra(BatteryManager.EXTRA_SCALE,0);
	    status= intent.getIntExtra(BatteryManager.EXTRA_STATUS,0);
	    String technology= intent.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY);
	    temperature= intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,0);
	    voltage= intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE,0);
		
	}
}
