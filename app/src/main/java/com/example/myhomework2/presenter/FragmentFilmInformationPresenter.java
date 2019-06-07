package com.example.myhomework2.presenter;

import com.example.myhomework2.model.movieInformation.MovieInformation;
import com.example.myhomework2.network.NetworkService;
import com.example.myhomework2.view.FilmInformation.FragmentFilmInformationView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FragmentFilmInformationPresenter {
    private final String FIELDS = "stars,country,body_text,trailer,title,images,age_restriction";
    private FragmentFilmInformationView fragmentFilmInformationView;

    public FragmentFilmInformationPresenter(FragmentFilmInformationView fragmentFilmInformationView) {
        this.fragmentFilmInformationView = fragmentFilmInformationView;
    }

    public void loadData(String id){

        getFilmInformation(FIELDS, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(
                        ee -> fragmentFilmInformationView.informationMethod(ee)
                )
                .subscribe();

    }
    private Observable<MovieInformation> getFilmInformation(String fields, String id) {
        return NetworkService.getInstance()//создание HTTP клиента и вызов метода с сервера
                .getJSONApi()
                .getMovieInformationById(id,fields);
    }
}
