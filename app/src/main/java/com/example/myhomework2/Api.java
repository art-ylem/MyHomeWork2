package com.example.myhomework2;


import com.example.myhomework2.movies.Movies;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("public-api/v1.4/movies")
    Observable<Movies> getMovieName(@Query("fields") String fields);

}
