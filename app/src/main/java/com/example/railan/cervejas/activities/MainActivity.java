package com.example.railan.cervejas.activities;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import com.example.railan.cervejas.R;
import com.example.railan.cervejas.contracts.BeerContract;
import com.example.railan.cervejas.databinding.ActivityMainBinding;
import com.example.railan.cervejas.dtos.Beer;
import com.example.railan.cervejas.presenters.BeersPresenter;
import com.example.railan.cervejas.repositories.BeerRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BeerContract.View {

    // dataBinding
    ActivityMainBinding binding;

    private BeersPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        presenter = new BeersPresenter(this, BeerRepository.getInstance(this.getApplicationContext()));
        presenter.loadBeers();
    }


    @Override
    public void showBeers(List<Beer> beers) {
        System.out.println(beers);
    }

    public void showError(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }
}
