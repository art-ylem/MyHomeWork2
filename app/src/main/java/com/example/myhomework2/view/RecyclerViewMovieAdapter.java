package com.example.myhomework2.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myhomework2.R;
import com.example.myhomework2.model.movies.Movies;
import com.example.myhomework2.model.movies.Result;

import io.reactivex.subjects.BehaviorSubject;

public class RecyclerViewMovieAdapter extends RecyclerView.Adapter<RecyclerViewMovieAdapter.MyViewHolder> {

    private Movies mDataSet;
    private Context context;
    private LayoutInflater mInflater;

    private BehaviorSubject<Result> itemClick = BehaviorSubject.create();



    public RecyclerViewMovieAdapter(Movies movies, Context context) {
            this.mDataSet = movies;
            this.context = context;
            this.mInflater = LayoutInflater.from(context);
        }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.recycler_view_row, viewGroup, false);
        return new MyViewHolder(view);
    }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.title.setText(mDataSet.getResults().get(position).getTitle());
            Glide.with(holder.movieImg.getContext()).load(mDataSet.getResults().get(position).getPoster().getImage()).into(holder.movieImg);
            holder.itemView.setOnClickListener(v -> itemClick.onNext(mDataSet.getResults().get(position)));

        }

        @Override
        public int getItemCount() {
            return mDataSet.getResults().size();
        }

    public BehaviorSubject<Result> getItemClick() {
        return itemClick;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView movieImg;


        MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movie_name);
            movieImg = itemView.findViewById(R.id.card_view_img);
        }
    }


}
