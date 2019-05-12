package com.example.myhomework2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.myhomework2.model.news.News;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FragmentNewsAdapter extends Fragment {


    private final String FIELDS = "publication_date,title,id,place,description,images";
    private RecycleViewNewsAdapter recycleViewNewsAdapter;
    private RecyclerView recyclerView;

    public static FragmentNewsAdapter newInstance() {
        FragmentNewsAdapter fragment = new FragmentNewsAdapter();
        return fragment;

    }
//    public void frag2(Fragment fragment){
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//        transaction.replace(R.id.information_fragment_container, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.news_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity mainActivity;
        mainActivity = (MainActivity) getActivity();
        recyclerView = view.findViewById(R.id.container_recycler_view_news);



        getNews()
                .subscribeOn(Schedulers.io())//где выполняется
                .observeOn(AndroidSchedulers.mainThread())//гд
                .doOnNext(news -> {
                    recycleViewNewsAdapter = new RecycleViewNewsAdapter(getContext(), news.getResults());
                    recyclerView.setAdapter(recycleViewNewsAdapter);
                })
                .subscribe();

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onAccountFragmentInteraction();
    }

    private Observable<News> getNews(){
        return NetworkService.getInstance()//создание HTTP клиента и вызов метода с сервера
                .getJSONApi()
                .getNews(30, FIELDS);
    }
}
