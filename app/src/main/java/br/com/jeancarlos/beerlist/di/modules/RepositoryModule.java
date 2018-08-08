package br.com.jeancarlos.beerlist.di.modules;

import br.com.jeancarlos.beerlist.data.BeersDataSource;
import br.com.jeancarlos.beerlist.data.local.LocalBeers;
import br.com.jeancarlos.beerlist.data.remote.RemoteBeers;
import br.com.jeancarlos.beerlist.di.scopes.Local;
import br.com.jeancarlos.beerlist.di.scopes.Remote;
import dagger.Binds;
import dagger.Module;

/**
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 08/08/18.
 * Jesus is alive!
 */
@Module
public abstract class RepositoryModule {

    @Binds
    @Local
    abstract BeersDataSource.BeersLocalDataSource provideLocalBeersData(LocalBeers localBeers);

    @Binds
    @Remote
    abstract BeersDataSource provideRemoteBeersData(RemoteBeers remoteBeers);
}
