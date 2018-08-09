package com.example.data.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * This class represents...
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 09/08/18.
 * Jesus is alive!
 */
@Dao
public interface BeerDao {

    @Query("SELECT * FROM beerEntity")
    LiveData<List<BeerEntity>> getAll();

    @Query("SELECT * FROM beerEntity WHERE name LIKE :name LIMIT 1")
    LiveData<List<BeerEntity>> findByName(String name);


    @Query("SELECT * FROM beerEntity WHERE isFavorite = 1")
    LiveData<List<BeerEntity>> getFavorites();

    @Insert
    void insertAll(BeerEntity... users);

    @Delete
    void delete(BeerEntity user);

}
