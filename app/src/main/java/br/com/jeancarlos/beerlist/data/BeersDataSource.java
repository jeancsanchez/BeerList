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

    void fetchBeers(@NonNull FetchBeersCallback callback);

    void searchBeerByName(String query, @NonNull SearchBeerCallback callback);

    void saveBeers(List<Beer> beers);


    interface FetchBeersCallback {

        void onBeersFetched(List<Beer> beers);

        void onBeersNotAvailable();

        void onBeersFetchError();
    }


    interface SearchBeerCallback {

        void onSearchBeerSuccess(List<Beer> beers);

        void onSearchBeerFailure();
    }
}
