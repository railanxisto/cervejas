package com.example.railan.cervejas.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.railan.cervejas.R;
import com.example.railan.cervejas.adapters.FavoriteBeersRecyclerViewAdapter;
import com.example.railan.cervejas.contracts.BeerFavoriteContract;
import com.example.railan.cervejas.databinding.ActivityFavoritesListBinding;
import com.example.railan.cervejas.dtos.Beer;
import com.example.railan.cervejas.persistence.AppDatabase;
import com.example.railan.cervejas.presenters.BeerFavoritePresenter;
import com.example.railan.cervejas.repositories.BeerRepository;

import java.util.ArrayList;
import java.util.List;

public class FavoritesListActivity extends AppCompatActivity implements BeerFavoriteContract.View {

    // dataBinding
    ActivityFavoritesListBinding binding;

    private BeerFavoritePresenter presenter;
    private FavoriteBeersRecyclerViewAdapter adapter;
    private List<Beer> beers = new ArrayList<>();
    AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_favorites_list);

        presenter = new BeerFavoritePresenter(this, BeerRepository.getInstance(this.getApplicationContext()));
        presenter.loadFavoriteBeers();

        setSupportActionBar(binding.mainToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setRecyclerViewLayout();

        binding.buscaEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                search(editable.toString());
            }
        });
    }

    public void setRecyclerViewLayout() {
        adapter = new FavoriteBeersRecyclerViewAdapter(beers, this);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.recyclerView.setHasFixedSize(false);
    }

    private void search(String query) {
        if (adapter != null) {
            adapter.getFilter().filter(query);
        }
    }

    private void showProgress(boolean isToShow) {
        if (isToShow) {
            binding.progressBar.setVisibility(View.VISIBLE);
            binding.recyclerView.setVisibility(View.GONE);
        } else {
            binding.progressBar.setVisibility(View.GONE);
            binding.recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showFavoriteBeers(List<Beer> beers) {
        adapter.setBeers(beers);
        showProgress(false);
    }

    @Override
    public void showEmptyFavoriteList() {
        // TODO: 09/08/18  Colocar uma mensagem de Lista Vazia
        showProgress(false);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
