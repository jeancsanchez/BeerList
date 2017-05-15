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

    /**
     * Executes the use case: Refresh beers list. This method makes the same
     * thing that {@link GetAllBeersUseCase#executeUseCase(BeersDataSource.FetchBeersCallback)}
     *
     * @param fetchBeersCallback A callback for handle the data
     */
    public void executeUseCase(BeersDataSource.FetchBeersCallback fetchBeersCallback) {
        mBeersRepository.fetchBeers(fetchBeersCallback);
    }
}
