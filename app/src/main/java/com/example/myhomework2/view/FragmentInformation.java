package com.example.myhomework2.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myhomework2.model.events.Date;
import com.example.myhomework2.presenter.FragmentInformationPresenter;
import com.example.myhomework2.R;
import com.example.myhomework2.model.postInformation.Dates;
import com.example.myhomework2.model.postInformation.InfoPost;

import java.text.SimpleDateFormat;
import java.util.List;

public class FragmentInformation extends Fragment implements FragmentInformationView{

    FragmentInformationPresenter fragmentInformationPresenter;

    private OnFragmentInteractionListener mListener;
    private String id;
    private TextView textViewdesc;
    private ImageView backBtn;
    private TextView textViewPrice;
    private TextView textViewLocation;
    private TextView textViewTitle;

    private TextView textData;
    private TextView publicationDate;
    private TextView textAge;
    private TextView likeText;
    private TextView text_under_desc_text;

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
        backBtn = view.findViewById(R.id.back_arrow_img);
        textViewPrice = view.findViewById(R.id.textViewPrice);
        textViewLocation = view.findViewById(R.id.place);
        textViewTitle = view.findViewById(R.id.textViewTitle);
        textData = view.findViewById(R.id.textData);
        publicationDate = view.findViewById(R.id.publicationDate);
        textAge = view.findViewById(R.id.textAge);
        likeText = view.findViewById(R.id.like_text);
        text_under_desc_text = view.findViewById(R.id.text_under_desc_text);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        backBtn.setOnClickListener(v -> getActivity().onBackPressed());
        fragmentInformationPresenter = new FragmentInformationPresenter(this);
        fragmentInformationPresenter.loadData(id);
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

    public String setDataStart(int dataStart, List<Date> dates){
        if(dataStart != 0 && dates != null) {
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
    public void informationMethod(InfoPost infoPost) {
        textViewdesc.setText(stripHtml(setTextString(infoPost.getDescription(), "Полное описание отсутствует")));
        textViewTitle.setText(setTextString(infoPost.getTitle(),"Название в разработке"));
        textViewLocation.setText(setTextString(infoPost.getLocation().getSlug(), "город проведения в разработке"));
        textViewPrice.setText(setPrice(infoPost.getPrice(), infoPost.getIs_free(),"Цена неee определена" ));
        publicationDate.setText(setTextString("Опубликовано: " + infoPost.getPublication_date(),"Дата публикации"));
        textAge.setText(setAgeRestriction(infoPost.getAge_restriction()));
        likeText.setText(setTextString(infoPost.getFavorites_count(),"100"));
//        textData.setText(setDataStart(infoPost.getDates().getStart(),infoPost.getDates()));
        text_under_desc_text.setText(stripHtml(setTextString(infoPost.getBody_text(),"")));
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
