package com.example.myhomework2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myhomework2.model.events.Events;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FragmentEventsAdapter extends Fragment {


    private final String FIELDS = "images,id,dates,short_title,title,place,location,categories,tagline,age_restriction,price,is_free,site_url";
    private RecycleViewEventsAdapter recycleViewEventsAdapter;
    private RecyclerView recyclerView;

    public static FragmentEventsAdapter newInstance() {
        FragmentEventsAdapter fragment = new FragmentEventsAdapter();
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
        View view = inflater.inflate(R.layout.events_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity mainActivity;
        mainActivity = (MainActivity) getActivity();
        recyclerView = view.findViewById(R.id.container_recycler_view_events);



        getLocations()
                .subscribeOn(Schedulers.io())//где выполняется
                .observeOn(AndroidSchedulers.mainThread())//гд
                .doOnNext(events -> {
                    recycleViewEventsAdapter = new RecycleViewEventsAdapter(getContext(), events.getResults());
                    recyclerView.setAdapter(recycleViewEventsAdapter);
                    recycleViewEventsAdapter.getItemClick().subscribe(results ->{
                        mainActivity.frag(InformationFragment.newInstance(results.getId().toString()));
                    } );
                })
                .subscribe();

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onAccountFragmentInteraction();
    }

    private Observable<Events> getLocations(){
        return NetworkService.getInstance()//создание HTTP клиента и вызов метода с сервера
                .getJSONApi()
                .getEvents(30, FIELDS);
    }
}
