package com.example.myhomework2.presenter;

import com.example.myhomework2.useCase.EventsUseCase;
import com.example.myhomework2.view.Events.FragmentEventsView;

public class FragmentEventsPresenter {

    FragmentEventsView fragmentMovieView;
    EventsUseCase eventsUseCase;

    public FragmentEventsPresenter(FragmentEventsView fragmentMovieView) {
        this.fragmentMovieView = fragmentMovieView;
        this.eventsUseCase = new EventsUseCase();
    }

    public void loadData(){

        eventsUseCase.loadEvents()
                .doOnNext(events -> fragmentMovieView.recyclerEvents(events.getResults()))
                .subscribe();
    }



}