package com.example.myhomework2.view.map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.google.android.gms.maps.model.MarkerOptions;


public class FragmentMap extends Fragment implements
        MapsView, OnMapReadyCallback {

    private GoogleMap mMap;
    private MapsPresenter mapsPresenter = new MapsPresenter(this);
    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    public static FragmentMap newInstance() {
        FragmentMap fragment = new FragmentMap();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_map, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void getEvents(Events data) {
        for(Result result : data.getResults()){
            if(result.getPlace().getCoords() != null){
//                Marker marker = mMap.addMarker(new MarkerOptions()
//                        .position(new LatLng(result.getPlace().getCoords().getLat(), result.getPlace().getCoords().getLon())));
//                        marker.setTag(result);
                Log.d("TAG", "getEvents: " + result.getPlace().getCoords());
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

        LatLng moscow = new LatLng(55.7558,37.6173);
        mMap.addMarker(new MarkerOptions().position(moscow).title("Marker in Moscow"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(moscow,10f));

        mapsPresenter.loadData();
    }

    @Override
    public void onDestroy() {
        mapsPresenter.dispose();
        super.onDestroy();
    }
}
