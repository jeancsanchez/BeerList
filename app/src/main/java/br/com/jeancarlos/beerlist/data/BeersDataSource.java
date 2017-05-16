package br.com.jeancarlos.beerlist.data;

import android.support.annotation.NonNull;

import java.util.List;

import br.com.jeancarlos.beerlist.features.beerslist.domain.model.Beer;

/**
 * This interface represents the entry point for any concrete class that wants access
 * data {@link Beer} objects
 *
 * @author Jean Carlos
 * @since 5/10/17
 */

public interface BeersDataSource {

    /**
     * Fetch all beers
     *
     * @param callback A callback for handle response
     */
    void fetchBeers(@NonNull FetchBeersCallback callback);

    /**
     * Search a beer by name
     *
     * @param callback A callback for handle response
     */
    void searchBeerByName(String query, @NonNull SearchBeerCallback callback);


    /**
     * This interface is for local repositories that wants save beers locally
     */
    interface BeersLocalDataSource extends BeersDataSource {
        /**
         * Save a list of beers on repository
         *
         * @param beers A list of beers to be save
         */
        void saveBeers(List<Beer> beers);

        /**
         * Save a favorite beer
         *
         * @param beer A {@link Beer}
         */
        void saveFavoriteBeer(Beer beer);


        /**
         * Get a list of favorite beers
         *
         * @param favoriteBeersCallback A callback for handle favorite beers data
         */
        void getFavoriteBeers(FavoriteBeersCallback favoriteBeersCallback);
    }


    /**
     * This interface is a callback for handle data from {@link #fetchBeers(FetchBeersCallback)}
     */
    interface FetchBeersCallback {

        void onBeersFetched(List<Beer> beers);

        void onBeersNotAvailable();

        void onBeersFetchError();
    }


    /**
     * This interface is a callback for handle data from {@link #searchBeerByName(String, SearchBeerCallback)}
     */
    interface SearchBeerCallback {

        void onSearchBeerSuccess(List<Beer> beers);

        void onSearchBeerFailure();
    }

    /**
     * This interface is a callback for handle data from {@link BeersLocalDataSource#getFavoriteBeers(FavoriteBeersCallback)}
     */
    interface FavoriteBeersCallback {

        void onFavoriteBeersFetched(List<Beer> beers);

        void onFavoriteBeersNotFound();
    }
}
