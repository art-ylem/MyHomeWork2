package com.example.myhomework2.view;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    public ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle("");
            //Для каждой активити в верхнем левом углу показываем стрелочку
            setDisplay(true);
        }
    }

    public void setDisplay(boolean display){
        actionBar.setDisplayHomeAsUpEnabled(display);

    }
    //Меняем название сверху на экране
    public void updateActivityTitle(String title){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle(title);
        }
    }

}
