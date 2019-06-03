package com.example.myhomework2.view.FilmInformation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.myhomework2.model.movieInformation.Image;
import com.example.myhomework2.view.FragmentScreenSlidePage;

import java.util.List;

public class FilmInformationScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

    List<Image> images;

    public FilmInformationScreenSlidePagerAdapter(FragmentManager fm, List<Image> images) {
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
