package com.example.data;

import android.support.annotation.NonNull;

import com.example.data.local.Local;
import com.example.data.remote.Remote;
import com.example.domain.BeersDataSource;
import com.example.domain.BeersRepository;
import com.example.domain.models.Beer;

import java.util.List;

import javax.inject.Inject;

/**
 * Concrete implementation of a {@link BeersDataSource} as a {@link Beer} repository.
 *
 * @author Jean Carlos
 * @since 5/10/17
 */

public class BeersRepositoryImpl implements BeersRepository {
    private final BeersDataSource mRemoteBeers;
    private final BeersLocalDataSource mLocalBeers;

    @Inject
    BeersRepositoryImpl(@Remote BeersDataSource remoteBeers, @Local BeersLocalDataSource localBeers) {
        mRemoteBeers = remoteBeers;
        mLocalBeers = localBeers;
    }

    @Override
    public void fetchBeers(@NonNull final FetchBeersCallback callback) {
        // Fetch locally first
        mLocalBeers.fetchBeers(callback);

        mRemoteBeers.fetchBeers(new BeersDataSource.FetchBeersCallback() {
            @Override
            public void onBeersFetched(List<Beer> beers) {
                callback.onBeersFetched(beers);

                // Save locally
                saveBeers(beers);
            }

            @Override
            public void onBeersNotAvailable() {
                callback.onBeersNotAvailable();
            }

            @Override
            public void onBeersFetchError() {
                callback.onBeersFetchError();
            }
        });
    }

    @Override
    public void searchBeerByName(String query, @NonNull SearchBeerCallback callback) {
        mRemoteBeers.searchBeerByName(query, callback);

    }

    @Override
    public void saveBeers(List<Beer> beers) {
        mLocalBeers.saveBeers(beers);
    }

    @Override
    public void saveFavoriteBeer(Beer beer) {
        mLocalBeers.saveFavoriteBeer(beer);
    }

    @Override
    public void removeFavoriteBeer(Beer beer) {
        mLocalBeers.removeFavoriteBeer(beer);
    }

    @Override
    public void getFavoriteBeers(FavoriteBeersCallback favoriteBeersCallback) {
        mLocalBeers.getFavoriteBeers(favoriteBeersCallback);
    }
}
