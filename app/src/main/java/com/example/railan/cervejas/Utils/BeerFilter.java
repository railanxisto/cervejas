package com.example.railan.cervejas.Utils;

import android.widget.Filter;

import com.example.railan.cervejas.dtos.Beer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by railan on 08/08/18.
 */

public class BeerFilter extends Filter {

    private List<Beer> beers;

    private BeerFilter.OnResultFilteredReceivedListener listener;

    public BeerFilter(List<Beer> beers, OnResultFilteredReceivedListener listener) {
        this.beers = beers;
        this.listener = listener;
        if (this.beers == null) {
            this.beers = new ArrayList<>();
        }
    }

    @Override
    protected FilterResults performFiltering(CharSequence text) {
        Filter.FilterResults filterResults = new Filter.FilterResults();

        List<Beer> filteredBeers = new ArrayList<>();
        for (Beer beer : beers) {
            if (beer.getName().toLowerCase(Locale.FRENCH).contains(text.toString().toLowerCase(Locale.FRENCH))
                    || beer.getTagline().toLowerCase(Locale.FRENCH).contains(text.toString().toLowerCase(Locale.FRENCH))
                    || beer.getFirstBrewed().contains(text.toString())) {
                filteredBeers.add(beer);
            }
        }

        filterResults.values = filteredBeers;
        filterResults.count = filteredBeers.size();

        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
        if (listener != null)
            listener.onResultFilteredReceived((List<Beer>) results.values);
    }

    public interface OnResultFilteredReceivedListener {
        void onResultFilteredReceived(List<Beer> filteredBeers);
    }
}
