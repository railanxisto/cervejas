package com.example.railan.cervejas.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.railan.cervejas.dtos.Beer;

/**
 * Created by railan on 08/08/18.
 */

@Database(entities = {Beer.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract BeerDAO beerDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "beer_database").build();
                }
            }
        }
        return INSTANCE;
    }

}
