package br.com.jeancarlos.beerlist.features.beersdetail.domain;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import br.com.jeancarlos.beerlist.data.BeersRepository;
import br.com.jeancarlos.beerlist.features.beerslist.domain.model.Beer;

/**
 * @author Jean Carlos
 * @since 5/16/17
 */

public class DisfavorUseCase {
    private final BeersRepository mBeersRepository;

    @Inject
    public DisfavorUseCase(@NonNull BeersRepository beersRepository) {
        this.mBeersRepository = beersRepository;
    }


    /**
     * Executes the use case: Disfavor a beer
     *
     * @param beer The beer
     */
    public void executeUseCase(Beer beer) {
        beer.setFavorite(false);
        mBeersRepository.removeFavoriteBeer(beer);
    }
}
