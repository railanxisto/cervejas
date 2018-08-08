package com.example.railan.cervejas.activities;

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
import com.example.railan.cervejas.presenters.BeersPresenter;
import com.example.railan.cervejas.repositories.BeerRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BeerContract.View {

    // dataBinding
    ActivityMainBinding binding;

    private BeersPresenter presenter;
    private BeersRecyclerViewAdapter adapter;
    private List<Beer> beers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        adapter = new BeersRecyclerViewAdapter(beers);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.recyclerView.setHasFixedSize(false);

        presenter = new BeersPresenter(this, BeerRepository.getInstance(this.getApplicationContext()));
        presenter.loadBeers();

        setSupportActionBar(binding.mainToolbar);

        binding.buscaEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //after the change calling the method and passing the search input
                //filter(editable.toString());
                search(editable.toString());
            }
        });
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
    public void showBeers(List<Beer> beers) {
        adapter.setBeers(beers);
        showProgress(false);
    }

    public void showError(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }
}
