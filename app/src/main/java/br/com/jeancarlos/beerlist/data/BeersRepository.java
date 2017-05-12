package br.com.jeancarlos.beerlist.data;

import javax.inject.Inject;

/**
 * @author Jean Carlos
 * @since 5/10/17
 */

public class BeersRepository implements BeersDataSource {
    private final BeersDataSource mRemoteBeers;
    private final BeersDataSource mLocalBeers;

    @Inject
    BeersRepository(@Remote BeersDataSource remoteBeers, @Local BeersDataSource localBeers) {
        mRemoteBeers = remoteBeers;
        mLocalBeers = localBeers;
    }

    @Override
    public void fetchBeers() {
        mRemoteBeers.fetchBeers();
        mLocalBeers.fetchBeers();
    }
}
