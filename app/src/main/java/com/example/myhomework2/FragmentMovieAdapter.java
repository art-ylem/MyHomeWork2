package com.example.myhomework2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myhomework2.movies.Movies;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class FragmentMovieAdapter extends Fragment {

    public static final String FIELD = "trailer,title,images,poster";
    RecyclerView recyclerView;
    RecyclerViewMovieAdapter recyclerViewMovieAdapter;


    public static FragmentMovieAdapter newInstance() {
        FragmentMovieAdapter f = new FragmentMovieAdapter();
        return f;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity mainActivity;
        mainActivity = (MainActivity) getActivity();
        recyclerView = view.findViewById(R.id.recycler_view);

        getNames()
                .subscribeOn(Schedulers.io())//где выполняется
                .observeOn(AndroidSchedulers.mainThread())//гд
                .doOnNext(movies -> {

                    recyclerViewMovieAdapter = new RecyclerViewMovieAdapter(movies, getContext());
                    recyclerView.setAdapter(recyclerViewMovieAdapter);
                    recyclerViewMovieAdapter.getItemClick().subscribe(result -> {
openWebPage(result.getTrailer());                    });

                } )
                .subscribe();

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movies_fragment,container,false);
    }

    private Observable<Movies> getNames(){
        return NetworkService.getInstance()//создание HTTP клиента и вызов метода с сервера
                .getJSONApi()
                .getMovieName(FIELD);
    }

    private void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    }
}
