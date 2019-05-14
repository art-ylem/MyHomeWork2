package com.example.myhomework2.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myhomework2.R;
import com.example.myhomework2.model.movies.Movies;
import com.example.myhomework2.presenter.FragmentMoviePresenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class FragmentMovie extends Fragment implements FragmentMovieView{

    RecyclerView recyclerView;
    RecyclerViewMovieAdapter recyclerViewMovieAdapter;
    FragmentMoviePresenter fragmentMoviePresenter;
    public CompositeDisposable compositeDisposable = new CompositeDisposable();




    public static FragmentMovie newInstance() {
        FragmentMovie f = new FragmentMovie();
        return f;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view_movies_container);
        fragmentMoviePresenter = new FragmentMoviePresenter(this);
        fragmentMoviePresenter.loadData();


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movies_fragment,container,false);
    }

    private void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    }

    @Override
    public void recycler(Movies movies) {
        recyclerViewMovieAdapter = new RecyclerViewMovieAdapter(movies, getContext());
        recyclerView.setAdapter(recyclerViewMovieAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        Disposable disposable = recyclerViewMovieAdapter.getItemClick().subscribe(result -> openWebPage(result.getTrailer()));
        compositeDisposable.add(disposable);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(compositeDisposable != null) compositeDisposable.dispose();
    }
}
