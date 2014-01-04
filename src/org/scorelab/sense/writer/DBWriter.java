package org.scorelab.sense.writer;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import org.scorelab.sense.dataCollector.Process.ProcessData;
import org.scorelab.sense.dataCollector.Process.ServiceData;
import org.scorelab.sense.dataCollector.Sensor.SensorData;
import org.scorelab.sense.dataCollector.Wifi.WifiData;
import org.scorelab.sense.util.SenseLog;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBWriter extends SQLiteOpenHelper{
	
	private static final String DB_NAME = "sense.db";
	private static final int DATABASE_VERSION = 1;
	  
	Context context;
	public DBWriter(Context context) {
	    super(context,  DB_NAME,null, DATABASE_VERSION);
	    this.context=context;
	  }
	
	
   
	
    
    
    private static final String DATABASE_CREATE ="";

	 
	 
	 
    
    private void executeSQLScript(SQLiteDatabase database, String dbname) {
    	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    	    byte buf[] = new byte[1024*3];
    	    int len;
    	    AssetManager assetManager = context.getAssets();
    	    InputStream inputStream = null;
    	         
    	    try{
    	        inputStream = assetManager.open(dbname);
    	        while ((len = inputStream.read(buf)) != -1) {
    	            outputStream.write(buf, 0, len);
    	        }
    	        outputStream.close();
    	        inputStream.close();
    	             
    	        String[] createScript = outputStream.toString().split(";");
    	        for (int i = 0; i < createScript.length; i++) {
    	                String sqlStatement = createScript[i].trim();
    	            // TODO You may want to parse out comments here
    	            if (sqlStatement.length() > 0) {
    	                    database.execSQL(sqlStatement + ";");
    	                }
    	        }
    	    } catch (IOException e){
    	        
    	    } catch (SQLException e) {
    	       SenseLog.i(e.getMessage());
    	    }
    	}
	@Override
	public void onCreate(SQLiteDatabase database) {
		
		executeSQLScript(database, "db.sql");
		SenseLog.i("database created");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
		/*String query;
        query = "DROP TABLE IF EXISTS sensor";
        database.execSQL(query);
        
        query = "DROP TABLE IF EXISTS process";
        database.execSQL(query);
        
        query = "DROP TABLE IF EXISTS service";
        database.execSQL(query);
        query = "DROP TABLE IF EXISTS wifi";
        database.execSQL(query);*/
        
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
        values.put("Sensorvalues", Values);
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
