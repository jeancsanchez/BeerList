package br.com.jeancarlos.beerlist.di.modules;

import com.example.data.BeersRepositoryImpl;
import com.example.data.local.Local;
import com.example.data.local.LocalBeers;
import com.example.data.remote.Remote;
import com.example.data.remote.RemoteBeers;
import com.example.domain.BeersDataSource;
import com.example.domain.BeersRepository;

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


    @Binds
    abstract BeersRepository providesBeersRepository(BeersRepositoryImpl beersRepository);
}
