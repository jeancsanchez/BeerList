package br.com.jeancarlos.beerlist.features.beerslist.domain.usecases;

import android.support.annotation.NonNull;

import br.com.jeancarlos.beerlist.data.BeersDataSource;
import br.com.jeancarlos.beerlist.data.BeersRepository;

/**
 * This use case handles refresh all beers
 *
 * @author Jean Carlos
 * @since 5/14/17
 */

public class RefreshBeersUseCase {
    private BeersRepository mBeersRepository;

    public RefreshBeersUseCase(@NonNull BeersRepository beersRepository) {
        this.mBeersRepository = beersRepository;
    }

    public void executeUseCase(String query, BeersDataSource.FetchBeersCallback fetchBeersCallback) {
        mBeersRepository.fetchBeers(fetchBeersCallback);
    }
}
