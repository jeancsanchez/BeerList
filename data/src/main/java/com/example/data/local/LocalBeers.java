package com.example.data.local;

import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.domain.BeersDataSource;
import com.example.domain.models.Beer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Concrete implementation of a {@link BeersLocalDataSource} and  as a db
 *
 * @author Jean Carlos
 * @since 5/10/17
 */

@Singleton
public class LocalBeers implements BeersDataSource.BeersLocalDataSource {

    private AppDatabase appDatabase;

    @Inject
    LocalBeers(@NonNull AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    public void fetchBeers(@NonNull final BeersDataSource.FetchBeersCallback callback) {
        appDatabase.beerDao().getAll().observeForever(new Observer<List<BeerEntity>>() {
            @Override
            public void onChanged(@Nullable List<BeerEntity> beersEntities) {

                if (beersEntities.size() == 0) {
                    callback.onBeersNotAvailable();

                } else {
                    List<Beer> beers = new ArrayList<>();

                    for (BeerEntity beerEntity : beersEntities) {
                        beers.add(beerEntity.mapOut());
                    }

                    callback.onBeersFetched(beers);
                }
            }
        });


    }

    @Override
    public void searchBeerByName(String query, @NonNull SearchBeerCallback callback) {
        List<BeerEntity> beersEntities =
                appDatabase.beerDao()
                        .findByName(query)
                        .getValue();

        if (beersEntities.size() == 0) {
            callback.onSearchBeerFailure();

        } else {
            List<Beer> beers = new ArrayList<>();

            for (BeerEntity beerEntity : beersEntities) {
                beers.add(beerEntity.mapOut());
            }

            callback.onSearchBeerSuccess(beers);
        }
    }


    @Override
    public void saveBeers(List<Beer> beers) {
        for (Beer beer : beers) {
            insertOrUpdate(beer);
        }
    }

    @Override
    public void saveFavoriteBeer(Beer beer) {
        insertOrUpdate(beer);
    }

    @Override
    public void removeFavoriteBeer(Beer beer) {
        insertOrUpdate(beer);
    }

    @Override
    public void getFavoriteBeers(FavoriteBeersCallback favoriteBeersCallback) {
        List<BeerEntity> beersEntities =
                appDatabase.beerDao()
                        .getFavorites()
                        .getValue();

        if (beersEntities.size() == 0) {
            favoriteBeersCallback.onFavoriteBeersNotFound();

        } else {
            List<Beer> beers = new ArrayList<>();

            for (BeerEntity beerEntity : beersEntities) {
                beers.add(beerEntity.mapOut());
            }

            favoriteBeersCallback.onFavoriteBeersFetched(beers);
        }
    }

    /**
     * Insert or update a beer
     *
     * @param beer The beer
     */
    private void insertOrUpdate(Beer beer) {
        appDatabase.beerDao().insertAll(BeerEntity.mapIn(beer));
    }
}
