package com.example.myhomework2;

import com.example.myhomework2.model.events.Events;
import com.example.myhomework2.view.FragmentEventsView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FragmentEventsPresenter {

    private final String FIELDS = "images,id,dates,short_title,title,place,location,categories,tagline,age_restriction,price,is_free,site_url";
    FragmentEventsView fragmentMovieView;

    public FragmentEventsPresenter(FragmentEventsView fragmentMovieView) {
        this.fragmentMovieView = fragmentMovieView;
    }

    public void loadData(){

        getLocations()
                .subscribeOn(Schedulers.io())//где выполняется
                .observeOn(AndroidSchedulers.mainThread())//гд
                .doOnNext(events -> fragmentMovieView.recyclerEvents(events.getResults()))
                .subscribe();
    }

    private Observable<Events> getLocations(){
        return NetworkService.getInstance()//создание HTTP клиента и вызов метода с сервера
                .getJSONApi()
                .getEvents(30, FIELDS);
    }


}
