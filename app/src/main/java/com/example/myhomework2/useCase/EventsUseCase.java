package com.example.myhomework2.useCase;

import com.example.myhomework2.model.events.Events;
import com.example.myhomework2.network.NetworkService;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EventsUseCase {

    private final String FIELDS = "images,id,dates,short_title,title,place,location,categories,tagline,age_restriction,price,is_free,site_url";
    private final String EXPAND = "place";

    public Observable<Events> loadEvents(){

        return NetworkService.getInstance()//создание HTTP клиента и вызов метода с сервера
                .getJSONApi()
                .getEvents(EXPAND, FIELDS)
                .subscribeOn(Schedulers.io())//где выполняется
                .observeOn(AndroidSchedulers.mainThread());
    }
}