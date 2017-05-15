package br.com.jeancarlos.beerlist.features.beerslist.domain.usecases;

import android.support.annotation.NonNull;

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

    public GetBeerByNameUseCase(@NonNull BeersRepository beersRepository) {
        this.mBeersRepository = beersRepository;
    }

    public void executeUseCase(String query, BeersDataSource.SearchBeerCallback searchBeerCallback) {
        mBeersRepository.searchBeerByName(query, searchBeerCallback);
    }
}
