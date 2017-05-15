package br.com.jeancarlos.beerlist.injection.components;

import javax.inject.Singleton;

import br.com.jeancarlos.beerlist.injection.modules.ApplicationModule;
import br.com.jeancarlos.beerlist.data.BeersRepository;
import br.com.jeancarlos.beerlist.injection.modules.BeerRepositoryModule;
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
