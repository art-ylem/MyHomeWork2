package com.example.myhomework2.view.Movie;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myhomework2.R;
import com.example.myhomework2.model.movies.Movies;
import com.example.myhomework2.presenter.FragmentMoviePresenter;
import com.example.myhomework2.view.BaseFragment;
import com.example.myhomework2.view.FilmInformation.FragmentFilmInformation;
import com.example.myhomework2.view.MainActivity;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class FragmentMovie extends BaseFragment implements FragmentMovieView {

    private MainActivity mainActivity;
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
        mainActivity = (MainActivity) getActivity();

        recyclerView = view.findViewById(R.id.recycler_view_movies_container);
        fragmentMoviePresenter = new FragmentMoviePresenter(this);
        fragmentMoviePresenter.loadData();

        updateActivityTitle("Фильмы");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movies_fragment,container,false);
    }



    @Override
    public void recycler(Movies movies) {
        recyclerViewMovieAdapter = new RecyclerViewMovieAdapter(movies, getContext());
        recyclerView.setAdapter(recyclerViewMovieAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Disposable disposable = recyclerViewMovieAdapter.getItemClick().subscribe(result -> mainActivity.launchFragWitchBackStack(FragmentFilmInformation.newInstance(result.getId().toString())));
        compositeDisposable.add(disposable);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(compositeDisposable != null) compositeDisposable.dispose();
    }
}
