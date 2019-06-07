package com.example.myhomework2.presenter;

import com.example.myhomework2.model.news.News;
import com.example.myhomework2.network.NetworkService;
import com.example.myhomework2.view.News.FragmentNewsView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FragmentNewsPresenter {
    private final String FIELDS = "publication_date,title,id,place,description,images";
    private FragmentNewsView fragmentNewsView;

    public FragmentNewsPresenter(FragmentNewsView fragmentNewsView) {
        this.fragmentNewsView = fragmentNewsView;
    }

    public void loadData(){

        getNews()
                .subscribeOn(Schedulers.io())//где выполняется
                .observeOn(AndroidSchedulers.mainThread())//гд
                .doOnNext(news -> fragmentNewsView.recyclerNews(news))
                .subscribe();
    }


    private Observable<News> getNews(){
        return NetworkService.getInstance()//создание HTTP клиента и вызов метода с сервера
                .getJSONApi()
                .getNews(30, FIELDS);
    }

}
