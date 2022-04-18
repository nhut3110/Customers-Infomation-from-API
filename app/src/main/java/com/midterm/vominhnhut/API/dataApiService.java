package com.midterm.vominhnhut.API;

import com.midterm.vominhnhut.model.DataApi;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class dataApiService {
    private static final String BASE_URL = "https://http://staff.vnuk.edu.vn:5000/";
    private dataApi api;

    public dataApiService(){
        api = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(dataApi.class);
    }

    public Call<List<DataApi>> getDogs(){
        return api.getData();
    }
}
