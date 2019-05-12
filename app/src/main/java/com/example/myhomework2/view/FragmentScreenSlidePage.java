package com.example.myhomework2.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myhomework2.R;

public class FragmentScreenSlidePage extends Fragment {

public static String url;
public ImageView imageView;


public static FragmentScreenSlidePage newInstance(String str) {
    FragmentScreenSlidePage fragment = new FragmentScreenSlidePage();
    Bundle args = new Bundle();
    args.putString("str", str);
    fragment.setArguments(args);
    return fragment;


}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getArguments().getString("str");

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