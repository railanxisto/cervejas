package com.example.railan.cervejas.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.railan.cervejas.R;
import com.example.railan.cervejas.Utils.BeerFilter;
import com.example.railan.cervejas.activities.MainActivity;
import com.example.railan.cervejas.databinding.BeerItemBinding;
import com.example.railan.cervejas.dtos.Beer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by railan on 07/08/18.
 */

public class BeersRecyclerViewAdapter extends RecyclerView.Adapter<BeersRecyclerViewAdapter.ViewHolder> implements Filterable {

    private List<Beer> beers = new ArrayList<>();
    private List<Beer> filteredBeers = new ArrayList<>();
    private MainActivity listener;


    public BeersRecyclerViewAdapter(@NonNull List<Beer> beers, MainActivity listener) {
        this.beers.addAll(beers);
        this.filteredBeers.addAll(beers);
        this.listener = listener;
        notifyDataSetChanged();
    }

    public void setBeers(@NonNull List<Beer> beers) {
        BeersRecyclerViewAdapter.this.beers = beers;
        BeersRecyclerViewAdapter.this.filteredBeers = beers;
        notifyDataSetChanged();
    }

    @Override
    public BeersRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        BeerItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.beer_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BeersRecyclerViewAdapter.ViewHolder holder, int position) {
        final Beer beer = filteredBeers.get(position);
        holder.bind(beer);
        holder.mBinding.layoutInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            listener.showDetailsActivity(beer);
            }
        });
        Picasso.get()
                .load(holder.mBinding.getBeer().getImageUrl().trim())
                .resize(100, 100)
                .centerInside()
                .into(holder.mBinding.imageView);
    }

    @Override
    public int getItemCount() {
        return filteredBeers.size();
    }

    @Override
    public Filter getFilter() {
        return new BeerFilter(beers, new BeerFilter.OnResultFilteredReceivedListener() {
            @Override
            public void onResultFilteredReceived(List<Beer> beers) {
                BeersRecyclerViewAdapter.this.filteredBeers = beers;
                notifyDataSetChanged();
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private BeerItemBinding mBinding;

        public ViewHolder(BeerItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(@NonNull Beer beer) {
            mBinding.setBeer(beer);
            mBinding.executePendingBindings();
        }
    }

}
