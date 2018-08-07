package com.example.railan.cervejas.contracts;

import com.example.railan.cervejas.dtos.Beer;

import java.util.List;

/**
 * Created by railan on 06/08/18.
 */

public interface BeerContract {
    interface View {
        void showBeers (List<Beer> beers);

        void showError(String error);
    }

    interface UserActionsListener {
        void loadBeers();
    }
}
