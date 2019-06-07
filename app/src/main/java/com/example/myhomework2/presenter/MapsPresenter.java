package com.example.myhomework2.presenter;

import com.example.myhomework2.App;
import com.example.myhomework2.model.events.Events;
import com.example.myhomework2.useCase.EventsUseCase;
import com.example.myhomework2.view.map.MapsView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MapsPresenter {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MapsView mapsView;

    @Inject
    EventsUseCase eventsUseCase;

    public MapsPresenter(MapsView mapsView) {
        this.mapsView = mapsView;
        App.getAppComponent().inject(this);
    }

    public void loadData(){

        Disposable disposable = eventsUseCase.loadEvents()
                .doOnNext(events -> coords(events))
                .subscribe();
        compositeDisposable.add(disposable);
    }

    private void coords(Events events){
        if(events != null){
            mapsView.getEvents(events);
        }
    }

    public void dispose(){
        compositeDisposable.dispose();
    }

}