package com.example.railan.cervejas.repositories;

import android.content.Context;

import com.example.railan.cervejas.dtos.Beer;
import com.example.railan.cervejas.network.Services;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by railan on 06/08/18.
 */

public class BeerRepository {

    private Retrofit mRetrofit = new Retrofit
            .Builder()
            .baseUrl(Services.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private Services mRetrofitService = mRetrofit.create(Services.class);

    private static final BeerRepository instance = new BeerRepository();

    private static Context context;

    public static BeerRepository getInstance(Context applicationContext) {
        BeerRepository.context = applicationContext.getApplicationContext();
        return instance;
    }

    private BeerRepository() {
        //ignore
    }

    public void loadBeers(final GetFaqListener listener) {
        try {
            mRetrofitService.getBeers().enqueue(new Callback<List<Beer>>() {
                @Override
                public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {
                    if (response.code() == 200 && response.body() != null) {
                        listener.success(response.body());
                    } else {
                        listener.onError("Erro desconhecido. Tente Novamente mais tarde");
                    }
                }

                @Override
                public void onFailure(Call<List<Beer>> call, Throwable t) {
                    handleThrowable(t, listener);
                }
            });
        } catch (Exception e) {
            handleThrowable(e, listener);
        }
    }

    public interface GetFaqListener extends OnErrorListener{
        void success(List<Beer> beers);
    }

    private void handleThrowable(Throwable t, OnErrorListener listener) {
        listener.onError("Erro Generico");
    }

    public interface OnErrorListener {
        void onError(String message);
    }

}
