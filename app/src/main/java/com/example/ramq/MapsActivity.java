package com.example.ramq;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.example.ramq.classes.maphelper.FetchURL;
import com.example.ramq.classes.maphelper.TaskLoadedCallback;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.ramq.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, TaskLoadedCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    private MarkerOptions pickUp, destination;

    private Polyline currentPolyline;

    private double distanceValue;
    private String timeText;
    private String distanceText;
    FetchURL urlInfoFetcher = new FetchURL(MapsActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        pickUp = new MarkerOptions().position(new LatLng(43.257497, -79.928025)).title("Location 1");
//        destination = new MarkerOptions().position(new LatLng(43.257807, -79.914485)).title("Location 2");

        pickUp = new MarkerOptions().position(new LatLng(43.258466, -79.918819)).title("Location 1");
        destination = new MarkerOptions().position(new LatLng(43.261651, -79.855866)).title("Location 2");

        urlInfoFetcher.execute(getUrl(pickUp.getPosition(), destination.getPosition(), "driving"), "driving");

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//        LatLng start = new LatLng(43.257497, -79.928025);
//        LatLng end = new LatLng(43.257807, -79.914485);
//        LatLng mid = new LatLng(43.257690, -79.920932);

        // Set the map type to Hybrid.
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        mMap.addMarker(pickUp);
        mMap.addMarker(destination);

        // Add a marker on the map coordinates.
//        googleMap.addMarker(new MarkerOptions()
//                .position(start)
//                .title("Fortinos"));
//        googleMap.addMarker(new MarkerOptions()
//                .position(end)
//                .title("Food Basics"));
//
//        // Draw route
//        Polyline line = googleMap.addPolyline(new PolylineOptions()
//                .add(start, end)
//                .width(5)
//                .color(Color.RED));
//
//        // Move the camera to the map coordinates and zoom in closer.
//        googleMap.moveCamera(CameraUpdateFactory.zoomTo(15));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(mid));
    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=AIzaSyB3pMTZDmqjIhC_xjMVBaAqs7j-5l19qRM";
        return url;
    }

    //After the URL is fetched, the below method is called to display the route
    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(43.261651, -79.855866),12.0f));
        distanceText = urlInfoFetcher.distanceText;
        distanceValue = urlInfoFetcher.distanceValue;
        timeText = urlInfoFetcher.timeText;
        Log.d("distanceTextLogFromMapsActivity", distanceText);
        Log.d("distanceValueLogFromMapsActivity", String.valueOf(distanceValue));
        Log.d("timeTextLogFromMapsActivity", timeText);
    }
}