package com.example.myhomework2.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myhomework2.R;
import com.example.myhomework2.model.news.Result;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import io.reactivex.subjects.BehaviorSubject;

public class RecycleViewNewsAdapter extends RecyclerView.Adapter<RecycleViewNewsAdapter.ViewHolder> {


    private ArrayList<Result> rData;
    private LayoutInflater mInflater;
    private Context context;

    BehaviorSubject<Result> itemClick = BehaviorSubject.create();

    public RecycleViewNewsAdapter(Context context, ArrayList<Result> data) {
        this.mInflater = LayoutInflater.from(context);
        this.rData = data;
        this.context = context;
    }


    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.recycler_view_row_news, viewGroup, false);
        return new ViewHolder(view);

    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.textData.setText(setDate(rData.get(position).getPublicationDate()));
        holder.textViewDesc.setText(stripHtml(setTextString(rData.get(position).getDescription(),"Описание в разработке")));
        holder.textViewTitle.setText(stripHtml(setTextString(rData.get(position).getTitle(), "Название в разработке")));

        holder.itemView.setOnClickListener(v -> itemClick.onNext(rData.get(position)));

    }

    public String setTextString(String txt, String elseStr){
        if(txt != null && txt != "" && txt != " ") return txt;
        else return elseStr;
    }

    public String setDate(int setDate){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long longDate = setDate * 1000L;
            java.util.Date date = new java.util.Date(longDate);
            return dateFormat.format(date);
        }



    // total number of rows
    @Override
    public int getItemCount() {
        return rData.size();
    }


    public BehaviorSubject<Result> getItemClick() {
        return itemClick;
    }

    public String stripHtml(String html)
    {
        return Html.fromHtml(html).toString();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textData;
        private TextView textViewDesc;

        ViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.news_title);
            textViewDesc = itemView.findViewById(R.id.news_desc);
            textData = itemView.findViewById(R.id.news_date);
        }
    }
}
