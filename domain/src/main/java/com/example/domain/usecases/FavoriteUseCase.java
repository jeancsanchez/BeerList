package com.example.domain.usecases;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import br.com.jeancarlos.beerlist.data.BeersRepository;

import com.example.domain.BeersRepository;
import com.example.domain.models.Beer;

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
