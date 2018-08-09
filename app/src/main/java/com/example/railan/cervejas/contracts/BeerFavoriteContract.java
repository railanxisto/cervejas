package com.example.railan.cervejas.contracts;

import com.example.railan.cervejas.dtos.Beer;

import java.util.List;

/**
 * Created by railan on 09/08/18.
 */

public interface BeerFavoriteContract {
    interface View {
        void showFavoriteBeers (List<Beer> beers);

        void showEmptyFavoriteList(boolean isToShow);
    }

    interface UserActionsListener {
        void loadFavoriteBeers();
    }
}
