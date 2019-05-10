package com.example.myhomework2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ScreenSlidePageFragment extends Fragment {

public static String url;
public ImageView imageView;


public static ScreenSlidePageFragment newInstance(String str) {
    ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
    url = str;
//        !!!!!!поменять на бандл
    return fragment;


}


@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
    ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
    imageView = rootView.findViewById(R.id.immg);
    Glide.with(imageView.getContext()).load(url).into(imageView);
    Log.e("log", "onBindViewHolder: " + url );


    return rootView;
}


}