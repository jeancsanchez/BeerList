package br.com.jeancarlos.beerlist.features.beersdetail.domain;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import br.com.jeancarlos.beerlist.data.BeersRepository;
import br.com.jeancarlos.beerlist.features.beerslist.domain.model.Beer;

/**
 * This use case favorites a beer
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
     * Executes the use case: Favorite a beer
     *
     * @param beer The beer
     */
    public void executeUseCase(Beer beer) {
        beer.setFavorite(true);
        mBeersRepository.saveFavoriteBeer(beer);
    }
}
