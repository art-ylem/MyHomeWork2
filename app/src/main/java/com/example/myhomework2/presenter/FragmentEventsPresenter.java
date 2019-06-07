package com.example.myhomework2.presenter;

import com.example.myhomework2.App;
import com.example.myhomework2.useCase.EventsUseCase;
import com.example.myhomework2.view.Events.FragmentEventsView;

import javax.inject.Inject;

public class FragmentEventsPresenter {

    FragmentEventsView fragmentMovieView;

    @Inject
    EventsUseCase eventsUseCase;

    public FragmentEventsPresenter(FragmentEventsView fragmentMovieView) {
        this.fragmentMovieView = fragmentMovieView;
        App.getAppComponent().inject(this);
    }

    public void loadData(){

        eventsUseCase.loadEvents()
                .doOnNext(events -> fragmentMovieView.recyclerEvents(events.getResults()))
                .subscribe();
    }



}