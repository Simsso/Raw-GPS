package com.timodenk.rawgps;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private GPSData gps;
    private ListView mainList;

    private Location lastLocation;

    private final int[] fields = new int[] {
            R.string.latitude,
            R.string.longitude,
            R.string.altitude,
            R.string.speed,
            R.string.accuracy,
            R.string.bearing,
            R.string.provider,
            R.string.time
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainList = (ListView) findViewById(R.id.listViewMain);
        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = getValueOfLocationObject(fields[position]);
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("gps-value", getString(fields[position]) + ": " + value);
                clipboard.setPrimaryClip(clip);

                Toast.makeText(getApplicationContext(), getString(R.string.copiedToClipboard), Toast.LENGTH_LONG).show();
            }
        });

        gps = new GPSData(this);
    }

    public void updateLocationInformation(Location newLocation) {
        this.lastLocation = newLocation;
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        for (int key : fields) {
            Map<String, String> datum = new HashMap<String, String>(2);
            datum.put("value", getValueOfLocationObject(key));
            datum.put("description", getString(key));
            data.add(datum);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_2,
                new String[] {"value", "description"},
                new int[] {android.R.id.text1,
                        android.R.id.text2});
        mainList.setAdapter(adapter);
    }

    private String getValueOfLocationObject(int key) {
        Location location = this.lastLocation;
        if (location == null) {
            return "";
        }
        switch (key) {
            case R.string.latitude:
                return String.valueOf(location.getLatitude());
            case R.string.longitude:
                return String.valueOf(location.getLongitude());
            case R.string.altitude:
                return String.valueOf(location.getAltitude());
            case R.string.speed:
                return String.valueOf(location.getSpeed());
            case R.string.accuracy:
                return String.valueOf(location.getAccuracy());
            case R.string.bearing:
                return String.valueOf(location.getBearing());
            case R.string.provider:
                return location.getProvider();
            case R.string.time:
                return String.valueOf(location.getTime());
            default:
                return "";
        }
    }
}
