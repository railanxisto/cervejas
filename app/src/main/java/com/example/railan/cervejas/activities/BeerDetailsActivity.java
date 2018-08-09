package com.example.railan.cervejas.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.railan.cervejas.R;
import com.example.railan.cervejas.databinding.ActivityBeerDetailsBinding;
import com.example.railan.cervejas.dtos.Beer;
import com.example.railan.cervejas.repositories.BeerRepository;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.railan.cervejas.activities.MainActivity.PARAM_BEER;

public class BeerDetailsActivity extends AppCompatActivity {

    //dataBinding
    ActivityBeerDetailsBinding binding;

    private Beer beer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_beer_details);

        setSupportActionBar(binding.mainToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent i = getIntent();
        Bundle extras = i.getExtras();
        if (extras != null) {
            beer = (Beer) extras.getSerializable(PARAM_BEER);
        }

        binding.setBeer(beer);

        updateButtonText();

        binding.favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                beer.setFavorited(!beer.isFavorited());
                updateButtonText();
                BeerRepository.getInstance(getApplicationContext()).updateBeerOnDB(beer);
            }
        });

        Picasso.get()
                .load(beer.getImageUrl().trim())
                .resize(250, 250)
                .centerInside()
                .into(binding.imageView);

    }

    public void updateButtonText() {
        if (beer.isFavorited()) {
            binding.favoriteButton.setText(getResources().getString(R.string.unfavorite));
        } else {
            binding.favoriteButton.setText(getResources().getString(R.string.favorite));
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
