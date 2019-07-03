package com.example.myhomework2.view.Events;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.myhomework2.model.events.Image;
import com.example.myhomework2.view.FragmentScreenSlidePage;

import java.util.List;

public class EventsScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

    List<Image> images;

    public EventsScreenSlidePagerAdapter(FragmentManager fm, List<Image> images) {
        super(fm);
        this.images = images;
    }


    @Override
    public Fragment getItem(int position) {

        return FragmentScreenSlidePage.newInstance(images.get(position).getImage());
    }

    @Override
    public int getCount() {
        return images.size();
    }
}