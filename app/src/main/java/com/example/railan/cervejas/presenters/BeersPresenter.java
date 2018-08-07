package com.example.railan.cervejas.presenters;

import android.support.annotation.NonNull;

import com.example.railan.cervejas.contracts.BeerContract;
import com.example.railan.cervejas.dtos.Beer;
import com.example.railan.cervejas.repositories.BeerRepository;

import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by railan on 06/08/18.
 */

public class BeersPresenter implements BeerContract.UserActionsListener {

    @NonNull
    private BeerContract.View viewListener;

    @NonNull
    private BeerRepository repository;

    public BeersPresenter(@NonNull BeerContract.View viewListener, @NonNull BeerRepository repository) {
        this.viewListener = checkNotNull(viewListener);
        this.repository = checkNotNull(repository);
    }

    @Override
    public void loadBeers() {
        repository.loadBeers(new BeerRepository.GetFaqListener() {
            @Override
            public void success(List<Beer> beers) {
                viewListener.showBeers(beers);
            }

            @Override
            public void onError(String message) {
                viewListener.showError(message);
            }
        });
    }
}
