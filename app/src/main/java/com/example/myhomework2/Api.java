package com.example.myhomework2;


import com.example.myhomework2.events.Events;
import com.example.myhomework2.movies.Movies;
import com.example.myhomework2.news.News;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("public-api/v1.4/movies")
    Observable<Movies> getMovieName(@Query("fields") String fields);

    @GET("public-api/v1.4/events")
    Observable<Events> getEvents(@Query("page_size") int pageSize, @Query("fields") String fields);

    @GET("public-api/v1.4/events")
    Observable<News> getNews(@Query("page_size") int pageSize, @Query("fields") String fields);

}
