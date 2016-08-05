package com.timodenk.rawgps;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private GPSData gps;
    private TextView textViewLat, textViewLng, textViewProvider, textViewAlt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewLat = (TextView) findViewById(R.id.textViewLatValue);
        textViewLng = (TextView) findViewById(R.id.textViewLngValue);
        textViewAlt = (TextView) findViewById(R.id.textViewAltValue);
        textViewProvider = (TextView) findViewById(R.id.textViewProviderValue);
    }

    public void setLatLng(double lat, double lng) {
        textViewLat.setText(String.valueOf(lat));
        textViewLng.setText(String.valueOf(lng));
    }

    public void setAltitude(double altitude) {
        textViewAlt.setText(String.valueOf(altitude) + " m");
    }

    public void setLocationProvider(String name) {
        textViewProvider.setText(name);
    }
}
