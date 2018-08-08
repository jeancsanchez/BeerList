package com.example.domain.usecases;

import android.support.annotation.NonNull;

import com.example.domain.BeersRepository;

import javax.inject.Inject;

import br.com.jeancarlos.beerlist.data.BeersDataSource;
import br.com.jeancarlos.beerlist.data.BeersRepository;

/**
 * This use case handles showing all favorite beers
 *
 * @author Jean Carlos
 * @since 5/16/17
 */

public class ShowFavoritesUseCase {
    private BeersRepository mBeersRepository;

    @Inject
    public ShowFavoritesUseCase(@NonNull BeersRepository beersRepository) {
        this.mBeersRepository = beersRepository;
    }


    /**
     * Executes the use case: Show favorite beers
     */
    public void executeUseCase(BeersDataSource.FavoriteBeersCallback favoriteBeersCallback) {
        mBeersRepository.getFavoriteBeers(favoriteBeersCallback);
    }
}
