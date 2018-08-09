package com.example.railan.cervejas.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverter;

import com.example.railan.cervejas.dtos.Beer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by railan on 08/08/18.
 */

@Dao
public interface BeerDAO {
    @Query("SELECT * FROM beer")
    List<Beer> getAllBeers();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAllBeers(List<Beer> beers);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBeer(Beer beer);

    @Query("Select * FROM beer WHERE isFavorited = 1")
    List<Beer> getFavoritedBeers();

}
