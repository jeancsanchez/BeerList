package br.com.jeancarlos.beerlist.data;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import br.com.jeancarlos.beerlist.features.beerslist.domain.model.Beer;
import br.com.jeancarlos.beerlist.injection.scopes.Local;
import br.com.jeancarlos.beerlist.injection.scopes.Remote;

/**
 * Concrete implementation of a {@link BeersDataSource} as a {@link Beer} repository.
 *
 * @author Jean Carlos
 * @since 5/10/17
 */

public class BeersRepository implements BeersDataSource, BeersDataSource.BeersLocalDataSource {
    private final BeersDataSource mRemoteBeers;
    private final BeersLocalDataSource mLocalBeers;

    @Inject
    BeersRepository(@Remote BeersDataSource remoteBeers, @Local BeersLocalDataSource localBeers) {
        mRemoteBeers = remoteBeers;
        mLocalBeers = localBeers;
    }

    @Override
    public void fetchBeers(@NonNull final FetchBeersCallback callback) {
        // Fetch locally first
        mLocalBeers.fetchBeers(callback);


        mRemoteBeers.fetchBeers(new FetchBeersCallback() {
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
