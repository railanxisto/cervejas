package com.example.railan.cervejas.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.railan.cervejas.R;
import com.example.railan.cervejas.adapters.BeersRecyclerViewAdapter;
import com.example.railan.cervejas.contracts.BeerContract;
import com.example.railan.cervejas.databinding.ActivityMainBinding;
import com.example.railan.cervejas.dtos.Beer;
import com.example.railan.cervejas.persistence.AppDatabase;
import com.example.railan.cervejas.presenters.BeersPresenter;
import com.example.railan.cervejas.repositories.BeerRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BeerContract.View {

    public static final String PARAM_BEER = "beer";

    // dataBinding
    ActivityMainBinding binding;

    private BeersPresenter presenter;
    private BeersRecyclerViewAdapter adapter;
    private List<Beer> beers = new ArrayList<>();
    AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        database = AppDatabase.getDatabase(this);

        presenter = new BeersPresenter(this, BeerRepository.getInstance(this.getApplicationContext()));
        presenter.loadBeers();

        setSupportActionBar(binding.mainToolbar);
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
        adapter = new BeersRecyclerViewAdapter(beers, this);
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

    public void showDetailsActivity(Beer selectedBeer) {
        Intent intent = new Intent(this, BeerDetailsActivity.class);
        intent.putExtra(PARAM_BEER, selectedBeer);
        startActivity(intent);
    }

    @Override
    public void showBeers(List<Beer> beers) {
        //database.beerDao().getAllBeers();
        adapter.setBeers(beers);
        showProgress(false);
    }

    public void showError(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }
}
