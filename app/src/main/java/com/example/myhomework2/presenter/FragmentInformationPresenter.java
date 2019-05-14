package com.example.myhomework2.presenter;

import com.example.myhomework2.model.postInformation.InfoPost;
import com.example.myhomework2.view.FragmentInformationView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FragmentInformationPresenter {

    private FragmentInformationView fragmentInformationView;

    public FragmentInformationPresenter(FragmentInformationView fragmentInformationView) {
        this.fragmentInformationView = fragmentInformationView;
    }

    public void loadData(String id){

        getInformation(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(
                        infoPost -> fragmentInformationView.informationMethod(infoPost)
                )
                .subscribe();

    }
    private Observable<InfoPost> getInformation(String id) {
        return NetworkService.getInstance()//создание HTTP клиента и вызов метода с сервера
                .getJSONApi()
                .getPostInformationById(id);
    }
}
