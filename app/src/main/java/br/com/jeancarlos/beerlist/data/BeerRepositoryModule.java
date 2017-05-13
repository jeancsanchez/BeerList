package br.com.jeancarlos.beerlist.data;

import javax.inject.Singleton;

import br.com.jeancarlos.beerlist.data.local.Local;
import br.com.jeancarlos.beerlist.data.local.LocalBeers;
import br.com.jeancarlos.beerlist.data.remote.Remote;
import br.com.jeancarlos.beerlist.data.remote.RemoteBeers;
import dagger.Binds;
import dagger.Module;

/**
 * This is used by Dagger to inject the required arguments into the {@link BeersRepository}.
 *
 * @author jeancarlos
 * @since 5/10/17
 */


@Module
abstract class BeerRepositoryModule {

    @Singleton
    @Binds
    @Local
    abstract BeersDataSource provideLocalBeersData(LocalBeers localBeers);

    @Singleton
    @Binds
    @Remote
    abstract BeersDataSource provideRemoteBeersData(RemoteBeers remoteBeers);
}
