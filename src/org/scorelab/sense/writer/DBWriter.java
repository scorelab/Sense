package org.scorelab.sense.writer;


import java.util.Vector;


import org.scorelab.sense.Sense;
import org.scorelab.sense.util.SenseLog;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import org.scorelab.sense.annotation.Attributes;

public class DBWriter extends SQLiteOpenHelper{
	
	private static final String DB_NAME = "senseData.db";
	private static final int DATABASE_VERSION = 1;
	private SQLiteDatabase senseDb;
	  
	Context context;
	public DBWriter() {
	    super(Sense.context,  DB_NAME,null, DATABASE_VERSION);
	    this.context=Sense.context;
	    senseDb = this.getWritableDatabase();
	  }
	

    /*
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
    	
    	*/
	@Override
	public void onCreate(SQLiteDatabase database) {
		
		//executeSQLScript(database, "db.sql");
		String query="";
		query=createTableFromClass("org.scorelab.sense.dataCollector.Process.ProcessData");
		database.execSQL(query);
		
		SenseLog.i("dr1"+query);
		query=createTableFromClass("org.scorelab.sense.dataCollector.Process.ServiceData");
		database.execSQL(query);
		SenseLog.i("dr2"+query);
		query=createTableFromClass("org.scorelab.sense.dataCollector.Sensor.SensorData");
		database.execSQL(query);
		SenseLog.i("dr3"+query);
		query=createTableFromClass("org.scorelab.sense.dataCollector.Wifi.WifiData");
		database.execSQL(query);
		SenseLog.i("dr4"+query);
		
		
		/*database.execSQL(createTableFromClass("org.scorelab.sense.dataCollector.Process.ProcessData"));
		database.execSQL(createTableFromClass("org.scorelab.sense.dataCollector.Process.ServiceData"));
		database.execSQL(createTableFromClass("org.scorelab.sense.dataCollector.Sensor.SensorData"));
		database.execSQL(createTableFromClass("org.scorelab.sense.dataCollector.Wifi.WifiData"));*/
		SenseLog.i("database createdn :)");
		
		
	}
	
	public String createTableFromClass(String className) {
		 
		Field[] fields = null;
 
		StringBuilder queryBuilder = new StringBuilder();
                   String primaryKey=", PRIMARY KEY  (";
		try {
			Class<?> clazz = Class.forName(className);
			fields = clazz.getDeclaredFields();
			String name = clazz.getSimpleName().replace("Data", "");
                     
			queryBuilder.append("CREATE TABLE " + name + " (");
		} catch (Exception e) {
			
		}
 
		boolean firstField = true;
               
 
		for (Field field : fields) {
			if (!firstField) {
				queryBuilder.append(", ");
			}
 
			queryBuilder.append(field.getName() + " ");
 
			if (String.class.isAssignableFrom(field.getType())) {
				queryBuilder.append("TEXT");
			}
 
			if (field.getType() == Integer.TYPE) {
				queryBuilder.append("INTEGER");
			}
                        
                        if ((field.getType() == Float.TYPE )||( field.getType() == Double.TYPE)) {
				queryBuilder.append("REAL");
			}
 
			Annotation annotation = field.getAnnotation(Attributes.class);
			if (annotation != null) {
				if (annotation instanceof Attributes) {
					Attributes attr = (Attributes) annotation;
					if (attr.primaryKey())
						//queryBuilder.append(" PRIMARY KEY");
                                                primaryKey=primaryKey+" "+field.getName()+ ",";
				}
			}
 
			firstField = false;
		}
                 primaryKey=primaryKey.substring(0, primaryKey.length()-1);
                 primaryKey=primaryKey+"));";
		queryBuilder.append(primaryKey);
 
		String query = queryBuilder.toString();
		    return query;
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
	
	/*
	public void insertSensor(Vector<SensorData> queryValues) {
		
		if(queryValues.size()<=0) return;
		SQLiteDatabase database = this.getWritableDatabase();
        
        
        
        
        for(int i=0; i <queryValues.size(); i++){
        	SensorData sensorInfo=queryValues.get(i);
        ContentValues values = new ContentValues();
        values.put("sensorName", sensorInfo.sensorName);
        values.put("sensorVendor", sensorInfo.sensorVendor);
        values.put("accuracyStatus", sensorInfo.accuracyStatus);
        values.put("sensorvalues", sensorInfo.sensorValues);
        values.put("collectTimestamp", sensorInfo.collectTimestamp);
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
	        values.put("processId", procInfo.processId);
	        values.put("processName", procInfo.processName);
	        values.put("totalPrivateDirty", procInfo.totalPrivateDirty);
	        values.put("totalPss", procInfo.totalPss);
	        values.put("totalSharedDirty", procInfo.totalSharedDirty);
	        values.put("timestamp", procInfo.timestamp);
	        database.insert("Process", null, values);
        }
        SenseLog.i("process data inserted");
        database.close();
    }
	
	
	public void insertService(Vector<ServiceData> queryValues){
		
		if(queryValues.size()<=0) return;
		SQLiteDatabase database = this.getWritableDatabase();
        for(int i=0; i <queryValues.size(); i++){
        	ServiceData serviceInfo=queryValues.get(i);
	        ContentValues values = new ContentValues();
	        values.put("processId", serviceInfo.processId);
	        values.put("processName",serviceInfo.processName);
	        values.put("serviceClassName", serviceInfo.serviceClassName);
	        values.put("timestamp", serviceInfo.timestamp);
	        database.insert("service", null, values);
        }
        SenseLog.i("service data inserted");
        database.close();
		
	}
	
	public void insertWifi(Vector<WifiData> queryValues){
		
		if(queryValues.size()<=0) return;
		SQLiteDatabase database = this.getWritableDatabase();
        for(int i=0; i <queryValues.size(); i++){
        	WifiData wifiInfo=queryValues.get(i);
	        ContentValues values = new ContentValues();
	        values.put("SSID", wifiInfo.SSID);
	        values.put("BSSID", wifiInfo.BSSID);
	        values.put("Capability", wifiInfo.Capability);
	        values.put("Timestamp", wifiInfo.TimeStamp);
	        database.insert("wifi", null, values);
	        
	        
        }
        
        SenseLog.i("wifi data inserted");
        database.close();
		
	}*/
	
	
public void close(){
	senseDb.close();
	
}
	
public void insertData(Vector queryValues){
		
		if(queryValues.size()<=0) {
			
			return;
		}
		
		
		//SenseLog.i(" testing "+ queryValues.get(0).getClass().getSimpleName());
		Class<?> cls=queryValues.get(0).getClass();
		String className=queryValues.get(0).getClass().getSimpleName().replace("Data", "");
		
    	Field[] fields = cls.getDeclaredFields();
        for(int i=0; i <queryValues.size(); i++){
        	
        	ContentValues values = new ContentValues();
        	for(int j=0; j < fields.length ; j++){
        		Field field=fields[j];
        		field.setAccessible(true);
        		
        		
        		try {
					if (String.class.isAssignableFrom(field.getType())) {					
			        		values.put(fields[j].getName(),field.get(queryValues.get(i)).toString());
					}else if (field.getType() == Integer.TYPE) {
		        		values.put(fields[j].getName(),field.getInt(queryValues.get(i)));
					}else if ((field.getType() == Float.TYPE )||( field.getType() == Double.TYPE)) {
		        		values.put(fields[j].getName(),field.getDouble(queryValues.get(i)));
					}
				
        		} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
        		
        		
        	}
	        
        	senseDb.insert(className, null, values);
	        
	        
        }
        SenseLog.i(className+" data inserted");
        
		
	}

	
	

}
