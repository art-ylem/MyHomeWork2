package com.example.javalesson.retrofit.screens;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle("");
            //Для каждой активити в верхнем левом углу показываем стрелочку
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    //Меняем название сверху на экране
    public void updateActivityTitle(String title){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //При нажатии в верхнем левом углу на стрелочку симулируем нажатие кнопки "Назад"
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
