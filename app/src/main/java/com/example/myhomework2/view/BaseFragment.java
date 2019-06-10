package com.example.javalesson.retrofit.screens;

import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {

    public void updateActivityTitle(String title){
        if (getActivity() instanceof BaseActivity){
            //Находим BaseActivity и меняем у нее название
            BaseActivity baseActivity = (BaseActivity) getActivity();
            baseActivity.updateActivityTitle(title);
        }
    }
}
