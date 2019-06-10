package com.example.javalesson.retrofit.screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.javalesson.retrofit.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class InfoFragment extends BaseFragment {

    @BindView(R.id.textView)
    TextView textView;

    Unbinder unbinder;

    public static InfoFragment newInstance() {
        InfoFragment fragment = new InfoFragment();
        return fragment;
    }

    //Метод в котором мы создаем наш интерфейс (подключаем лейоут)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);//Находим xml
        updateActivityTitle(getString(R.string.about_app));
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
