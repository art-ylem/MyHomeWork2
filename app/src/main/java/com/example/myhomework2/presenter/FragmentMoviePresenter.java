package com.example.myhomework2.presenter;

import com.example.myhomework2.model.movies.Movies;
import com.example.myhomework2.view.FragmentMovieView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FragmentMoviePresenter {

    public static final String FIELD = "trailer,title,images,poster";
    FragmentMovieView fragmentMovieView;

    public FragmentMoviePresenter(FragmentMovieView fragmentMovieView) {
        this.fragmentMovieView = fragmentMovieView;
    }

    public void loadData(){


        getNames()
                .subscribeOn(Schedulers.io())//где выполняется
                .observeOn(AndroidSchedulers.mainThread())//гд
                .doOnNext(movies -> fragmentMovieView.recycler(movies))
                .subscribe();
    }


    private Observable<Movies> getNames(){
        return NetworkService.getInstance()//создание HTTP клиента и вызов метода с сервера
                .getJSONApi()
                .getMovieName(FIELD);
    }


}
