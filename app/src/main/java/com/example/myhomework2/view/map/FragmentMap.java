package com.example.myhomework2.view.map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myhomework2.R;
import com.example.myhomework2.model.events.Coords;
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

import java.util.ArrayList;


public class FragmentMap extends Fragment implements
        MapsView, OnMapReadyCallback {

    private GoogleMap mMap;
    private MapsPresenter mapsPresenter = new MapsPresenter(this);
    private static final String ARG_PARAM1 = "param1";
    public ArrayList<Coords> coords = new ArrayList<Coords>();

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
            if(result.getPlace() != null && result.getPlace().getCoords() != null){
                coords.add(result.getPlace().getCoords());
                if(coords != null){
                    for(Coords coord : coords){
                        Marker marker =  mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(coord.getLat(),coord.getLon())));
                        marker.setTag(result);
                    }

                }
//                Log.d("TAG", "getEvents: " + result.getPlace().getCoords().getLon() + "___" + result.getPlace().getCoords().getLat());

            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

        LatLng moscow = new LatLng(55.7558,37.6173);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(moscow,10f));
        mapsPresenter.loadData();

    }


    @Override
    public void onDestroy() {
        mapsPresenter.dispose();
        super.onDestroy();
    }
}