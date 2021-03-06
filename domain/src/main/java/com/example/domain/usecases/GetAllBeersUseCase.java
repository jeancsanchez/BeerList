package com.example.domain.usecases;

import android.support.annotation.NonNull;

import com.example.domain.BeersDataSource;
import com.example.domain.BeersRepository;

import javax.inject.Inject;

/**
 * This use case handles getting all beers
 *
 * @author Jean Carlos
 * @since 5/14/17
 */

public class GetAllBeersUseCase {

    private final BeersRepository mBeersRepository;

    @Inject
    public GetAllBeersUseCase(@NonNull BeersRepository beersRepository) {
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
