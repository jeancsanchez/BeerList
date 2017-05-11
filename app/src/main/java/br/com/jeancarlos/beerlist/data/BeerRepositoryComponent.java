package br.com.jeancarlos.beerlist.data;

import javax.inject.Singleton;

import br.com.jeancarlos.beerlist.ApplicationModule;
import dagger.Component;

/**
 * @author jeancarlos
 * @since 5/10/17
 */

@Singleton
@Component(modules = {BeerRepositoryModule.class, ApplicationModule.class})
public interface BeerRepositoryComponent {
    BeersRepository provideBeersRepository();
}
