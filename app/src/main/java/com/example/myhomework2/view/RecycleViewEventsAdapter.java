package com.example.myhomework2.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myhomework2.R;
import com.example.myhomework2.model.events.Date;
import com.example.myhomework2.model.events.Result;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.BehaviorSubject;

public class RecycleViewEventsAdapter extends RecyclerView.Adapter<RecycleViewEventsAdapter.ViewHolder> {


    private ArrayList<Result> rData;
    private LayoutInflater mInflater;
    private Context context;

    BehaviorSubject<Result> itemClick = BehaviorSubject.create();

    public RecycleViewEventsAdapter(Context context, ArrayList<Result> data) {
        this.mInflater = LayoutInflater.from(context);
        this.rData = data;
        this.context = context;
    }


    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.recycler_view_row_events, viewGroup, false);
        return new ViewHolder(view);

    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(context instanceof MainActivity){
            MainActivity mainActivity = (MainActivity) context;
            ScreenSlidePagerAdapter screenSlidePagerAdapter = new ScreenSlidePagerAdapter(mainActivity.getSupportFragmentManager(),rData.get(position).getImages());
            holder.mPager.setAdapter(screenSlidePagerAdapter);

        }

        holder.textViewLocation.setText(setTextString(rData.get(position).getLocation().toString(),"город проведения в разработке"));
        holder.textViewPrice.setText(setPrice(rData.get(position).getPrice(), rData.get(position).getIsFree(), "Цену уточняйте"));
        holder.textAgeRestriction.setText(setAgeRestriction(rData.get(position).getAgeRestriction()));

        holder.textData.setText("С " + (setDataStart(rData.get(position).getDates().get(0).getStart(), rData.get(position).getDates()))
                + " По " + setDataEnd(rData.get(position).getDates().get(0).getEnd(),rData.get(position).getDates()));

        holder.itemView.setOnClickListener(v -> itemClick.onNext(rData.get(position)));
        holder.textViewTitle.setText(setTextString(rData.get(position).getTitle(), "Название в разработке"));

    }

    public String setTextString(String txt, String elseStr){
        if(txt != null && txt != "" && txt != " ") return txt;
        else return elseStr;
    }

    public String setPrice(String price, Boolean free, String elsee){
        if (free) return "Бесплатно";
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


    // total number of rows
    @Override
    public int getItemCount() {
        return rData.size();
    }


    public BehaviorSubject<Result> getItemClick() {
        return itemClick;
    }



    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewPrice;
        private TextView textViewLocation;
        private TextView textViewTitle;
        private TextView textAgeRestriction;
        private TextView textData;
        private ViewPager mPager;


//        private FrameLayout frameLayout;


        ViewHolder(View itemView) {
            super(itemView);
            textViewPrice = itemView.findViewById(R.id.price);
            textViewLocation = itemView.findViewById(R.id.text_under_price);
            textViewTitle = itemView.findViewById(R.id.text_under_img);
            textAgeRestriction = itemView.findViewById(R.id.age);
            textData = itemView.findViewById(R.id.date);
            mPager = itemView.findViewById(R.id.box_img);

//            frameLayout = itemView.findViewById(R.id.information_frag);

        }

    }


}
