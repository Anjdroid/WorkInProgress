package com.example.tnuv.work_in_progress;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MapsView extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Map<Marker, Class> allMarkersMap = new HashMap<Marker, Class>();
    private HashMap<Marker, Integer> mHashMap = new HashMap<Marker, Integer>();
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_view);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        db = new DBHelper(getApplicationContext());

        //category view
        ImageButton catButton = (ImageButton) findViewById(R.id.catView);
        catButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MapsView.this, CategoryTasks.class);
                startActivity(intent);
            }
        });
        //add task
        Button addButton = (Button) findViewById(R.id.addTask);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MapsView.this, AddTask.class);
                startActivity(intent);
            }
        });
        //location view
        ImageButton locButton = (ImageButton) findViewById(R.id.listView);
        locButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MapsView.this, AllTasks.class);
                startActivity(intent);
            }
        });
        //settings
        ImageButton setButton = (ImageButton) findViewById(R.id.set);
        setButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MapsView.this, Settings.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        List<Category> cats = db.getAllTags();
        for (Category c : cats) {
            String loc = c.getLocation();
            String[] parts = loc.split(",");
            double a = Double.parseDouble(parts[0]);
            double b = Double.parseDouble(parts[1]);
            LatLng locc = new LatLng(a, b);
            Marker m = mMap.addMarker(new MarkerOptions().position(locc).title(c.getName()));
            allMarkersMap.put(m, CategoryTasks.class);
            mHashMap.put(m, c.getID());
        }
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Class cls = allMarkersMap.get(marker);
                int idd = mHashMap.get(marker);
                Category cat = db.getCategory(idd);
                Intent intent = new Intent(MapsView.this, cls);
                intent.putExtra("idC", Integer.toString(cat.getID()));
                startActivity(intent);
            }
        });
        LatLngBounds bounds = new LatLngBounds(
                new LatLng(45.42351587, 13.54064941), new LatLng(46.87802963, 16.71981812));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bounds.getCenter(), 7));
    }
}