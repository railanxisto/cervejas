package com.example.railan.cervejas.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.railan.cervejas.R;
import com.example.railan.cervejas.databinding.ActivityBeerDetailsBinding;
import com.example.railan.cervejas.dtos.Beer;
import com.squareup.picasso.Picasso;

import static com.example.railan.cervejas.activities.MainActivity.PARAM_BEER;

public class BeerDetailsActivity extends AppCompatActivity {

    //dataBinding
    ActivityBeerDetailsBinding binding;

    private Beer beer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_beer_details);

        Intent i = getIntent();
        Bundle extras = i.getExtras();
        if (extras != null) {
            beer = (Beer) extras.getSerializable(PARAM_BEER);
        }

        binding.setBeer(beer);

        Picasso.get()
                .load(beer.getImageUrl().trim())
                .resize(250, 250)
                .centerInside()
                .into(binding.imageView);


        setSupportActionBar(binding.mainToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
