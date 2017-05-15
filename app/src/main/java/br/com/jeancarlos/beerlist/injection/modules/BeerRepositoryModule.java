package br.com.jeancarlos.beerlist.injection.modules;

import javax.inject.Singleton;

import br.com.jeancarlos.beerlist.data.BeersDataSource;
import br.com.jeancarlos.beerlist.data.BeersRepository;
import br.com.jeancarlos.beerlist.injection.scopes.Local;
import br.com.jeancarlos.beerlist.data.local.LocalBeers;
import br.com.jeancarlos.beerlist.injection.scopes.Remote;
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
public abstract class BeerRepositoryModule {

    @Singleton
    @Binds
    @Local
    abstract BeersDataSource provideLocalBeersData(LocalBeers localBeers);

    @Singleton
    @Binds
    @Remote
    abstract BeersDataSource provideRemoteBeersData(RemoteBeers remoteBeers);
}
