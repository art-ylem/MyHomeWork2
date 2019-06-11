package com.example.myhomework2.view;

import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {

    public void updateActivityTitle(String title){
        if (getActivity() instanceof BaseActivity){
            //Находим BaseActivity и меняем у нее название
            BaseActivity baseActivity = (BaseActivity) getActivity();
            baseActivity.updateActivityTitle(title);



        }
    }

    public void updateDisplay(boolean b){
        if (getActivity() instanceof BaseActivity){
            //Находим BaseActivity и меняем у нее название
            BaseActivity baseActivity = (BaseActivity) getActivity();
            baseActivity.setDisplay(b);


        }
    }
}
