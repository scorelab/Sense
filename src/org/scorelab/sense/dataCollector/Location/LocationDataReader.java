package org.scorelab.sense.dataCollector.Location;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

import org.scorelab.sense.Sense;
import org.scorelab.sense.dataCollector.DataReader;
import org.scorelab.sense.util.SenseLog;
import org.scorelab.sense.writer.DBWriter;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class LocationDataReader extends DataReader implements LocationListener {
	protected LocationManager locationManager;
	private Context mContext;
	private boolean canGetLocation = false;
	
	Location location; // location
    double latitude; // latitude
    double longitude; // longitude
 
    private Vector<LocationData> vectLocation = new Vector<LocationData>();
    // The minimum distance to change Updates in meters
   // private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
 
    // The minimum time between updates in milliseconds
    //private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
    
	public LocationDataReader() {
		mContext=Sense.context;
		getLocation();
	}
	
	  public Location getLocation() {
	        try {
	            locationManager = (LocationManager) mContext.getSystemService(mContext.LOCATION_SERVICE);
	 
	            // getting GPS status
	            boolean isGPSEnabled = locationManager
	                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
	 
	            // getting network status
	            boolean isNetworkEnabled = locationManager
	                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
	 
	            if (!isGPSEnabled && !isNetworkEnabled) {
	                // no network provider is enabled
	            } else {
	                this.canGetLocation = true;
	                // First get location from Network Provider
	                if (isNetworkEnabled) {
	                    locationManager.requestLocationUpdates(
	                            LocationManager.NETWORK_PROVIDER,0,0,this);
	                           // MIN_TIME_BW_UPDATES,
	                           // MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
	                    
	                    if (locationManager != null) {
	                        location = locationManager
	                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	                        if (location != null) {
	                            latitude = location.getLatitude();
	                            longitude = location.getLongitude();
	                        }
	                    }
	                }
	                // if GPS Enabled get lat/long using GPS Services
	                if (isGPSEnabled) {
	                    if (location == null) {
	                        locationManager.requestLocationUpdates(
	                                LocationManager.GPS_PROVIDER,0,0,this);
	                                //MIN_TIME_BW_UPDATES,
	                                //MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
	                                
	                        SenseLog.i("GPS Enabled");
	                        if (locationManager != null) {
	                            location = locationManager
	                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
	                            if (location != null) {
	                                latitude = location.getLatitude();
	                                longitude = location.getLongitude();
	                            }
	                        }
	                    }
	                }
	            }
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 
	        return location;
	    }
	  
	  public boolean canGetLocation() {
	        return this.canGetLocation;
	    }
	  
	  public void stopUsingGPS(){
	        if(locationManager != null){
	            locationManager.removeUpdates(LocationDataReader.this);
	        }      
	    }
	  
	  public double getLatitude(){
	        if(location != null){
	            latitude = location.getLatitude();
	        }
	         
	        // return latitude
	        return latitude;
	    }
	     
	    /**
	     * Function to get longitude
	     * */
	    public double getLongitude(){
	        if(location != null){
	            longitude = location.getLongitude();
	        }
	         
	        // return longitude
	        return longitude;
	    }
	  
	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void run() {
		SenseLog.d("running -Location");
		try {
			TimeUnit.MILLISECONDS.sleep(10000);
			vectLocation.add(new LocationData(getLatitude(), getLongitude()));
			DBWriter dbW=new DBWriter();
			dbW.insertData(vectLocation);
			dbW.close();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


}
