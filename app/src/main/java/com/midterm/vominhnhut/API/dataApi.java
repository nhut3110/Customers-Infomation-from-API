package com.midterm.vominhnhut.API;

import com.midterm.vominhnhut.model.DataApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface dataApi {
    @GET("static/data/data.json")
    Call<List<DataApi>> getData();
}
