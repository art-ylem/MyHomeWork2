package com.example.myhomework2.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static NetworkService mInstance;
    private static final String BASE_URL = "https://kudago.com/";
    private Retrofit mRetrofit;

    private NetworkService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();//Для того чтобы писалось в лог
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()//Парень, который связывает теюя с интернетом
                .addInterceptor(interceptor);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)//куда обращаться
                .addConverterFactory(GsonConverterFactory.create())//из json в объект
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//это для работы rx
                .client(client.build())//сборка и запуск
                .build();
    }

    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }


    public Api getJSONApi() {
        return mRetrofit.create(Api.class);//привязываем Api.class (интерфейс) к Retrofit
    }

}
