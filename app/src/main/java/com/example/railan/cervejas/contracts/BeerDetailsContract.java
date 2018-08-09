package com.example.railan.cervejas.contracts;

import com.example.railan.cervejas.dtos.Beer;

import java.util.List;

/**
 * Created by railan on 09/08/18.
 */

public interface BeerDetailsContract {
    interface View {
        void showFavoriteBeers (List<Beer> beers);

        void showEmptyFavoriteList();
    }

    interface UserActionsListener {
        void loadFavoriteBeers();
    }
}
