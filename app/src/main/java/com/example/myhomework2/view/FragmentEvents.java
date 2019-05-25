package com.example.myhomework2.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.myhomework2.presenter.FragmentEventsPresenter;
import com.example.myhomework2.R;
import com.example.myhomework2.model.events.Result;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class FragmentEvents extends Fragment implements FragmentEventsView {


    private RecycleViewEventsAdapter recycleViewEventsAdapter;
    private RecyclerView recyclerView;
    private MainActivity mainActivity;
    private FragmentEventsPresenter fragmentEventsPresenter;
    public  CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Switch aSwitch;

    public static FragmentEvents newInstance() {
        FragmentEvents fragment = new FragmentEvents();
        return fragment;

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.events_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.container_recycler_view_events);
        mainActivity = (MainActivity) getActivity();
        fragmentEventsPresenter = new FragmentEventsPresenter(this);
        fragmentEventsPresenter.loadData();
        aSwitch = view.findViewById(R.id.switch_grid_liner);



    }

    @Override
    public void recyclerEvents(ArrayList<Result> data) {
        recycleViewEventsAdapter = new RecycleViewEventsAdapter(getContext(), data);
        recyclerView.setAdapter(recycleViewEventsAdapter);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)         recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                    else recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));

            }
        });
        Disposable disposable = recycleViewEventsAdapter.getItemClick().subscribe(results -> mainActivity.frag(FragmentInformation.newInstance(results.getId().toString())));
        compositeDisposable.add(disposable);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onAccountFragmentInteraction();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(compositeDisposable != null) compositeDisposable.dispose();
    }
}
