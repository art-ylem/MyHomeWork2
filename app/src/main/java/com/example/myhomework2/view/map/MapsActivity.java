package com.example.myhomework2.view.map;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.myhomework2.R;
import com.example.myhomework2.model.events.Events;
import com.example.myhomework2.model.events.Result;
import com.example.myhomework2.presenter.MapsPresenter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, MapsView {

    private GoogleMap mMap;
    private MapsPresenter mapsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mapsPresenter = new MapsPresenter(this);
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

        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

        LatLng moscow = new LatLng(59.9342802,30.3350986);
        mMap.addMarker(new MarkerOptions().position(moscow).title("Marker in Moscow"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(moscow,5f));

        mapsPresenter.loadData();

    }


    @Override
    public void getEvents(Events data) {
        for(Result result : data.getResults()){
            if(result.getPlace().getCoords() != null){
                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(result.getPlace().getCoords().getLat(), result.getPlace().getCoords().getLon()))
                        .title(result.getTitle()));
            }
        }
    }
}
