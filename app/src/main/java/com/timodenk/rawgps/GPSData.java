package com.timodenk.rawgps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class GPSData implements LocationListener {
    private MainActivity ui;

    public GPSData(MainActivity ui) {
        this.ui = ui;
        listen();
    }

    protected void listen() {
        LocationManager locationManager = (LocationManager) ui.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        double lat = location.getLatitude(), lng = location.getLongitude(), altitude = location.getAltitude();
        ui.setLatLng(lat, lng);
        ui.setAltitude(altitude);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        ui.setLocationProvider(provider);
    }

    @Override
    public void onProviderEnabled(String provider) {
        ui.setLocationProvider(provider);
    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
