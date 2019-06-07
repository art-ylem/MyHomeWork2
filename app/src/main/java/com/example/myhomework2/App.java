package com.example.myhomework2;

import android.app.Application;

import com.example.myhomework2.dagger.AppComponent;
import com.example.myhomework2.dagger.DaggerAppComponent;
import com.example.myhomework2.dagger.NetworkModule;

public class App extends Application {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = createAppComponent();
    }

    private AppComponent createAppComponent(){
        return DaggerAppComponent
                .builder()
                .networkModule(new NetworkModule())
                .build();
    }
}
