package br.com.jeancarlos.beerlist.features.beerslist.domain.usecases;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import br.com.jeancarlos.beerlist.data.BeersDataSource;
import br.com.jeancarlos.beerlist.data.BeersRepository;

/**
 * This use case handles search bear by name
 *
 * @author Jean Carlos
 * @since 5/14/17
 */

public class GetBeerByNameUseCase {
    private BeersRepository mBeersRepository;

    @Inject
    public GetBeerByNameUseCase(@NonNull BeersRepository beersRepository) {
        this.mBeersRepository = beersRepository;
    }


    /**
     * Executes the use case: Get beer by name
     *
     * @param query              The string query for searching beer by name on server
     * @param searchBeerCallback A callback for handle the data
     */
    public void executeUseCase(String query, BeersDataSource.SearchBeerCallback searchBeerCallback) {
        mBeersRepository.searchBeerByName(query, searchBeerCallback);
    }
}
