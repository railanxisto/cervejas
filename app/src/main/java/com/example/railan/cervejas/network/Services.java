package com.example.railan.cervejas.network;

import com.example.railan.cervejas.dtos.Beer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by railan on 06/08/18.
 */

public interface Services {

    String BASE_URL = "https://api.punkapi.com/v2/";

    @GET("beers/")
    Call<List<Beer>> getBeers();
}
