package com.example.myhomework2.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myhomework2.presenter.FragmentNewsPresenter;
import com.example.myhomework2.R;
import com.example.myhomework2.model.news.News;

public class FragmentNews extends Fragment implements FragmentNewsView{


    private RecycleViewNewsAdapter recycleViewNewsAdapter;
    private RecyclerView recyclerView;
    private FragmentNewsPresenter fragmentNewsPresenter;

    public static FragmentNews newInstance() {
        FragmentNews fragment = new FragmentNews();
        return fragment;

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity mainActivity;
        mainActivity = (MainActivity) getActivity();
        recyclerView = view.findViewById(R.id.container_recycler_view_news);
        fragmentNewsPresenter = new FragmentNewsPresenter(this);
        fragmentNewsPresenter.loadData();



    }

    @Override
    public void recyclerNews(News news) {

        recycleViewNewsAdapter = new RecycleViewNewsAdapter(getContext(), news.getResults());
        recyclerView.setAdapter(recycleViewNewsAdapter);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onAccountFragmentInteraction();
    }

}
