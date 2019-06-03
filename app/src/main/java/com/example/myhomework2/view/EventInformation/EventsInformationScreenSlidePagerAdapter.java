package com.example.myhomework2.view.EventInformation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.myhomework2.model.postInformation.Images;
import com.example.myhomework2.view.FragmentScreenSlidePage;

import java.util.List;

public class EventsInformationScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

    List<Images> images;

    public EventsInformationScreenSlidePagerAdapter(FragmentManager fm, List<Images> images) {
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
