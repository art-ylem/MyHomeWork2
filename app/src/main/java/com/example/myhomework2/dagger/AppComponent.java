package com.example.myhomework2.dagger;

import com.example.myhomework2.presenter.FragmentEventsPresenter;
import com.example.myhomework2.presenter.MapsPresenter;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {NetworkModule.class})
public interface AppComponent {
    void inject(FragmentEventsPresenter fragmentEventsPresenter);
    void inject(MapsPresenter mapsPresenter);
}
