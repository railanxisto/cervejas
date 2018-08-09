package com.example.railan.cervejas.presenters;

import android.support.annotation.NonNull;

import com.example.railan.cervejas.contracts.BeerFavoriteContract;
import com.example.railan.cervejas.dtos.Beer;
import com.example.railan.cervejas.repositories.BeerRepository;

import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by railan on 09/08/18.
 */

public class BeerFavoritePresenter implements BeerFavoriteContract.UserActionsListener {

    @NonNull
    private BeerFavoriteContract.View viewListener;

    @NonNull
    private BeerRepository repository;

    public BeerFavoritePresenter(@NonNull BeerFavoriteContract.View viewListener, @NonNull BeerRepository repository) {
        this.viewListener = checkNotNull(viewListener);
        this.repository = checkNotNull(repository);
    }

    @Override
    public void loadFavoriteBeers() {
        repository.getFavoritesFromDB(new BeerRepository.GetFromBDListener() {
            @Override
            public void success(List<Beer> beers) {
                if (beers.size() != 0) {
                    viewListener.showFavoriteBeers(beers);
                } else {
                    viewListener.showEmptyFavoriteList();
                }
            }

            @Override
            public void onError(String message) {
                viewListener.showEmptyFavoriteList();
            }
        });
    }

}
