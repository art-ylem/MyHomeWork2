package com.example.myhomework2.view.EventInformation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myhomework2.R;
import com.example.myhomework2.model.events.Date;
import com.example.myhomework2.model.postInformation.Dates;
import com.example.myhomework2.model.postInformation.InfoPost;
import com.example.myhomework2.presenter.FragmentInformationPresenter;
import com.example.myhomework2.view.BaseFragment;
import com.example.myhomework2.view.MainActivity;

import java.text.SimpleDateFormat;
import java.util.List;

public class FragmentInformation extends BaseFragment implements FragmentInformationView{

    FragmentInformationPresenter fragmentInformationPresenter;
    private MainActivity mainActivity;

    private OnFragmentInteractionListener mListener;
    private String id;
    private TextView textViewdesc;
    private TextView textViewPrice;
    private TextView textViewLocation;
    private TextView textViewTitle;
    private ImageView backBtn;

    private TextView textData;
    private TextView textAge;
    private TextView likeText;
    private TextView text_under_desc_text;
    private ViewPager viewPager;
    private TextView viewPagerIndicator;

    public static FragmentInformation newInstance(String id) {
        FragmentInformation fragment = new FragmentInformation();
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
        View view = inflater.inflate(R.layout.information_fragment, container, false);
        textViewdesc = view.findViewById(R.id.description_text);
        textViewPrice = view.findViewById(R.id.textViewPrice);
        textViewLocation = view.findViewById(R.id.place);
        textViewTitle = view.findViewById(R.id.textViewTitle);
        textData = view.findViewById(R.id.textData);
        textAge = view.findViewById(R.id.textAge);
        likeText = view.findViewById(R.id.like_text);
        text_under_desc_text = view.findViewById(R.id.text_under_desc_text);
        viewPager = view.findViewById(R.id.box_img_event_information);
        viewPagerIndicator = view.findViewById(R.id.view_pager_indicator);
        backBtn = view.findViewById(R.id.back_btn_event);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentInformationPresenter = new FragmentInformationPresenter(this);
        fragmentInformationPresenter.loadData(id);
        mainActivity = (MainActivity) getActivity();

        backBtn.setOnClickListener(v -> mainActivity.onBackPressed());
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

    public String setData(String data, Dates[] dates, String elsee){
        if(data != " " && data != "" && data != null && dates != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(Integer.parseInt(data) * 1000);
        } else return elsee;
    }

    public String setDataStart(int dataStart){
        if(dataStart != 0) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long longDate = dataStart * 1000L;
            java.util.Date date = new java.util.Date(longDate);
            return dateFormat.format(date);
        } else return "Дата начала еще не определена";
    }

    public String setDataEnd(int dataEnd, List<Date> dates){
        if(dataEnd != 0 && dates != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long longDate = dataEnd * 1000L;
            java.util.Date date = new java.util.Date(longDate);
            return dateFormat.format(date);
        } else return "Дата окончания еще не определена";
    }

    public String cityName(String slug){
        switch (slug){
            case "ekb":
                return "Екатеринбург";
            case "krasnoyarsk":
                return "Красноярск";
            case "krd":
                return "Краснодар";
            case "kzn":
                return "Казань";
            case "msk":
                return "Москва";
            case "nnv":
                return "Нижний Новгород";
            case "nsk":
                return "Новосибирск";
            case "smr":
                return "Самара";
            case "sochi":
                return "Сочи";
            case "spb":
                return "Санкт-Петербург";
            case "ufa":
                return "Уфа";
            case "vbg":
                return "Выборг";
            default:
                return "Город";
        }
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

    private void initImagesGallery(InfoPost infoPost){
        if (infoPost.getImages() != null) {
            viewPagerIndicator.setText(1 + "/" + infoPost.getImages().size());
            EventsInformationScreenSlidePagerAdapter screenSlidePagerAdapter = new EventsInformationScreenSlidePagerAdapter(mainActivity.getSupportFragmentManager(),infoPost.getImages());
            viewPager.setAdapter(screenSlidePagerAdapter);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {}

                @Override
                public void onPageSelected(int i) {
                    //Во время перелистывания картинок, мы должны менять номер картинки в нижнем правом углу
                    viewPagerIndicator.setText((i + 1) + "/" + infoPost.getImages().size());
                }

                @Override
                public void onPageScrollStateChanged(int i) {}
            });
        }
    }

    @Override
    public void informationMethod(InfoPost infoPost) {
        textViewdesc.setText(stripHtml(setTextString(infoPost.getDescription(), "Полное описание отсутствует")));
        textViewTitle.setText(setTextString(infoPost.getTitle(),"Название в разработке"));
        textViewLocation.setText(setTextString(cityName(infoPost.getLocation().getSlug()), "город проведения в разработке"));
        textViewPrice.setText(setPrice(infoPost.getPrice(), infoPost.getIs_free(),"Цена неee определена" ));
        textAge.setText(setAgeRestriction(infoPost.getAge_restriction()));
        likeText.setText(setTextString(infoPost.getFavorites_count(),"100"));
        textData.setText(setDataStart(Integer.parseInt(infoPost.getDates()[0].getStart())));
        text_under_desc_text.setText(stripHtml(setTextString(infoPost.getBody_text(),"")));
        initImagesGallery(infoPost);

        updateActivityTitle(infoPost.getTitle());
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
