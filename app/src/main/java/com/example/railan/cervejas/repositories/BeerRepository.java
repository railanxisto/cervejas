package com.example.railan.cervejas.repositories;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.example.railan.cervejas.dtos.Beer;
import com.example.railan.cervejas.network.Services;
import com.example.railan.cervejas.persistence.AppDatabase;
import com.example.railan.cervejas.persistence.BeerDAO;

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

    AppDatabase database;

    private static final BeerRepository instance = new BeerRepository();

    private static Context context;

    public static BeerRepository getInstance(Context applicationContext) {
        BeerRepository.context = applicationContext.getApplicationContext();
        return instance;
    }

    private BeerRepository() {
        //ignore
    }

    public void loadBeers(final GetBeersListener listener) {

        try {
            mRetrofitService.getBeers().enqueue(new Callback<List<Beer>>() {
                @Override
                public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {
                    if (response.code() == 200 && response.body() != null) {
                        saveToDB(response.body());
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

    @SuppressLint("StaticFieldLeak")
    public void updateBeerOnDB(final Beer beer) {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... params) {
                AppDatabase.getDatabase(context).beerDao().insertBeer(beer);
                return 1;
            }

            @Override
            protected void onPostExecute(Integer agentsCount) {}
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void saveToDB(final List<Beer> beers) {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... params) {
                AppDatabase.getDatabase(context).beerDao().insertAllBeers(beers);
                return 1;
            }

            @Override
            protected void onPostExecute(Integer agentsCount) {}
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void getFavoritesFromDB(final GetFromBDListener listener) {
        new  AsyncTask<Void, Void, List<Beer>>() {
            @Override
            protected List<Beer> doInBackground(Void... params) {
                return AppDatabase.getDatabase(context).beerDao().getFavoritedBeers();
            }

            @Override
            protected void onPostExecute(List<Beer> beers) {
                listener.success(beers);
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void getBeersFromDB(final GetFromBDListener listener) {
        new  AsyncTask<Void, Void, List<Beer>>() {
            @Override
            protected List<Beer> doInBackground(Void... params) {
                return AppDatabase.getDatabase(context).beerDao().getAllBeers();
            }

            @Override
            protected void onPostExecute(List<Beer> beers) {
                listener.success(beers);
            }
        }.execute();
    }

    public interface GetBeersListener extends OnErrorListener{
        void success(List<Beer> beers);
    }

    public interface GetFromBDListener extends OnErrorListener{
        void success(List<Beer> beers);
    }

    private void handleThrowable(Throwable t, OnErrorListener listener) {
        listener.onError("Erro Generico");
    }

    public interface OnErrorListener {
        void onError(String message);
    }

}
