package com.example.railan.cervejas.adapters;

import android.databinding.DataBindingUtil;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.railan.cervejas.R;
import com.example.railan.cervejas.databinding.BeerItemBinding;
import com.example.railan.cervejas.dtos.Beer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by railan on 07/08/18.
 */

public class BeersRecyclerViewAdapter extends RecyclerView.Adapter<BeersRecyclerViewAdapter.ViewHolder> {

    private List<Beer> beers = new ArrayList<>();

    public BeersRecyclerViewAdapter(@NonNull List<Beer> beers) {
        this.beers.addAll(beers);
    }

    @NonNull
    @Override
    public BeersRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        BeerItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.beer_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BeersRecyclerViewAdapter.ViewHolder holder, int position) {
        Beer beer = beers.get(position);
        holder.bind(beer);
        holder.mBinding.layoutInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Beer Clicada");
            }
        });
    }

    @Override
    public int getItemCount() {
        return beers.size();
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
