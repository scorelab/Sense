package org.scorelab.sense.writer;

import java.util.Vector;

import org.scorelab.sense.dataCollector.Process.ProcessData;
import org.scorelab.sense.dataCollector.Process.ServiceData;
import org.scorelab.sense.dataCollector.Sensor.SensorData;
import org.scorelab.sense.dataCollector.Wifi.WifiData;
import org.scorelab.sense.util.SenseLog;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBWriter extends SQLiteOpenHelper{
	
	private static final String DB_NAME = "sense.db";
	  private static final int DATABASE_VERSION = 1;
	public DBWriter(Context context) {
	    super(context,  DB_NAME,null, DATABASE_VERSION);
	  }
	
	
	
	 private static final String DATABASE_CREATE = "CREATE TABLE 'process' ("+
			"  'Id' int(11) NOT NULL default '0',"+
			"  'Name' text,"+
			"  'PrivateDirtyData' int(11) default NULL,"+
			"  'TotalPss' int(11) default NULL,"+
			"  'totalSharedDirty' int(11) default NULL,"+
			"  'timestamp' timestamp NOT NULL default '0000-00-00 00:00:00',"+
			"  PRIMARY KEY  ('timestamp','Id')"+
			") ;"+
			"CREATE TABLE 'sensor' ("+
			"  'Name' varchar(255) NOT NULL default '',"+
			"  'Vendor' text,"+
			"  'accuracy' int(11) default NULL,"+
			"  'values' text,"+
			"  'collectTime' timestamp NOT NULL default '0000-00-00 00:00:00',"+
			"  'timestamp' timestamp NULL default NULL,"+
			"  PRIMARY KEY  ('collectTime','Name')"+
			");"+
			"CREATE TABLE 'service' ("+
			"  'Id' int(11) NOT NULL default '0',"+
			"  'Name' text,"+
			"  'ClassName' varchar(800) NOT NULL default '',"+
			"  'timestamp' timestamp NOT NULL default '0000-00-00 00:00:00' ,"+
			"  PRIMARY KEY  ('timestamp','ClassName','Id')"+
			") ;"+
			"CREATE TABLE 'wifi' ("+
			"  'SSID' varchar(512) NOT NULL default '',"+
			"  'BSSID' text,"+
			"  'Capabilities' text,"+
			"  'TimeStamp' timestamp NOT NULL default '0000-00-00 00:00:00',"+
			"  PRIMARY KEY  ('TimeStamp','SSID')"+
			") ;";
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
		SenseLog.i("database created");
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
		String query;
        query = "DROP TABLE IF EXISTS sensor";
        database.execSQL(query);
        
        query = "DROP TABLE IF EXISTS process";
        database.execSQL(query);
        
        query = "DROP TABLE IF EXISTS service";
        database.execSQL(query);
        query = "DROP TABLE IF EXISTS wifi";
        database.execSQL(query);
        
        onCreate(database);
		
	}
	
	public void insertSensor(Vector<SensorData> queryValues) {
      
		if(queryValues.size()<=0) return;
		SQLiteDatabase database = this.getWritableDatabase();
        
        
        
        
        for(int i=0; i <queryValues.size(); i++){
        	SensorData sensorInfo=queryValues.get(i);
        ContentValues values = new ContentValues();
        values.put("Name", sensorInfo.sensorName);
        values.put("Vendor", sensorInfo.sensorVendor);
        
        String Values="";
		for(int m=0;m<sensorInfo.sensorValues.length;m++){
			Values+=sensorInfo.sensorValues[m]+" , ";
			
		}
        values.put("accuracy", sensorInfo.accuracyStatus);
        values.put("values", Values);
        values.put("CollectTime", sensorInfo.collectTimestamp);
        values.put("timestamp", sensorInfo.timestamp);
        database.insert("sensor", null, values);
        }
        SenseLog.i("sensor data inserted");
        database.close();
    }
	
	public void insertProcess(Vector<ProcessData> queryValues) {
	      
		if(queryValues.size()<=0) return;
		SQLiteDatabase database = this.getWritableDatabase();
        for(int i=0; i <queryValues.size(); i++){
        	ProcessData procInfo=queryValues.get(i);
	        ContentValues values = new ContentValues();
	        values.put("Id", procInfo.processId);
	        values.put("Name", procInfo.processName);
	        values.put("PrivateDirtyData", procInfo.totalPrivateDirty);
	        values.put("TotalPss", procInfo.totalPss);
	        values.put("totalSharedDirty", procInfo.totalSharedDirty);
	        values.put("timestamp", procInfo.timestamp);
	        database.insert("process", null, values);
        }
        SenseLog.i("process data inserted");
        database.close();
    }
	
	
	public void insertService(Vector<ServiceData> queryValues){
		
		if(queryValues.size()<=0) return;
		SQLiteDatabase database = this.getWritableDatabase();
        for(int i=0; i <queryValues.size(); i++){
        	ServiceData procInfo=queryValues.get(i);
	        ContentValues values = new ContentValues();
	        values.put("Id", procInfo.processId);
	        values.put("Name", procInfo.processName);
	        values.put("ClassName", procInfo.serviceClassName);
	        values.put("timestamp", procInfo.timestamp);
	        database.insert("service", null, values);
        }
        SenseLog.i("service data inserted");
        database.close();
		
	}
	
	public void insertWifi(Vector<WifiData> queryValues){
		
		if(queryValues.size()<=0) return;
		SQLiteDatabase database = this.getWritableDatabase();
        for(int i=0; i <queryValues.size(); i++){
        	WifiData procInfo=queryValues.get(i);
	        ContentValues values = new ContentValues();
	        values.put("SSID", procInfo.SSID);
	        values.put("BSSID", procInfo.BSSID);
	        values.put("Capabilities", procInfo.Capability);
	        values.put("timestamp", procInfo.TimeStamp);
	        database.insert("wifi", null, values);
        }
        
        SenseLog.i("wifi data inserted");
        database.close();
		
	}
	
	
	
	

}
