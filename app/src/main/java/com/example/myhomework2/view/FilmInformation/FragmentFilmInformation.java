package com.example.myhomework2.view.FilmInformation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myhomework2.R;
import com.example.myhomework2.model.movieInformation.MovieInformation;
import com.example.myhomework2.presenter.FragmentFilmInformationPresenter;
import com.example.myhomework2.view.MainActivity;
import com.example.myhomework2.view.Movie.FragmentMovie;

public class FragmentFilmInformation extends Fragment implements FragmentFilmInformationView{

    FragmentFilmInformationPresenter fragmentFilmInformationPresenter;
    private MainActivity mainActivity;

    private OnFragmentInteractionListener mListener;
    private String id;
    private TextView starsText;
    private ImageView backBtn;
    private TextView descText;
    private TextView movieTitle;
    private CardView playCardView;
    private Context context;
    private ViewPager imgPager;



    public static FragmentFilmInformation newInstance(String id) {
        FragmentFilmInformation fragment = new FragmentFilmInformation();
        Bundle args = new Bundle();
        args.putString("idKey", id);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getString("idKey");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.film_information_fragment, container, false);
        descText = view.findViewById(R.id.description_text);
        starsText = view.findViewById(R.id.stars_text);
        movieTitle = view.findViewById(R.id.movie_title);
        playCardView = view.findViewById(R.id.recyclerCardView2);
        backBtn = view.findViewById(R.id.back_arrow_img_movie_information);
        imgPager = view.findViewById(R.id.box_img_movie_information);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainActivity = (MainActivity) getActivity();

        backBtn.setOnClickListener(v -> mainActivity.frag(FragmentMovie.newInstance()));

        fragmentFilmInformationPresenter = new FragmentFilmInformationPresenter(this);
        fragmentFilmInformationPresenter.loadData(id);
    }

    private void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    }

    public String setTextString(String title, String elsee){
        if(title != null && title != "" && title != " ") return title;
        else return elsee;
    }
    public String setPrice(String price, String free, String elsee){
        if (free == "true") return "Бесплатно";
        else if(price != null && price != "" && price != " ") return price;
        else return elsee;
    }

    public String setAgeRestriction(String ageRestriction){
        if(ageRestriction != null && ageRestriction != "" && ageRestriction != " "){
            if(ageRestriction.endsWith("+")) return ageRestriction;
            else return ageRestriction + "+";
        }
        else return "0+";
    }





    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void informationMethod(MovieInformation movieInformation) {
        starsText.setText(setTextString(movieInformation.getStars(), " "));
        descText.setText(stripHtml(setTextString(movieInformation.getBodyText(), " ")));
        movieTitle.setText(setTextString(movieInformation.getTitle() + "  " + setAgeRestriction(movieInformation.getAgeRestriction()), " "));
        playCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage(movieInformation.getTrailer());
            }
        });
        if(context instanceof MainActivity){
            mainActivity = (MainActivity) context;
        }
        FilmInformationScreenSlidePagerAdapter screenSlidePagerAdapter = new FilmInformationScreenSlidePagerAdapter(mainActivity.getSupportFragmentManager(),movieInformation.getImages());
        imgPager.setAdapter(screenSlidePagerAdapter);

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onAccountFragmentInteraction();
    }


    public String stripHtml(String html)
    {
        return Html.fromHtml(html).toString();
    }
}
