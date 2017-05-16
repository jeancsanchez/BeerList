package br.com.jeancarlos.beerlist.features.beersdetail.domain;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import br.com.jeancarlos.beerlist.data.BeersDataSource;
import br.com.jeancarlos.beerlist.data.BeersRepository;

/**
 * This use case handles getting all beers
 *
 * @author Jean Carlos
 * @since 5/14/17
 */

public class FavoriteUseCase {

    private final BeersRepository mBeersRepository;

    @Inject
    public FavoriteUseCase(@NonNull BeersRepository beersRepository) {
        this.mBeersRepository = beersRepository;
    }


    /**
     * Executes the use case: Get all beers
     *
     * @param fetchBeersCallback
     */
    public void executeUseCase(BeersDataSource.FetchBeersCallback fetchBeersCallback) {
        mBeersRepository.fetchBeers(fetchBeersCallback);
    }
}
