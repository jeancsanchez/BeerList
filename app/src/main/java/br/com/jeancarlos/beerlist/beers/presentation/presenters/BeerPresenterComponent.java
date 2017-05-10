package br.com.jeancarlos.beerlist.beers.presentation.presenters;

import javax.inject.Singleton;

import br.com.jeancarlos.beerlist.beers.presentation.ui.MainActivity;
import dagger.Component;
import dagger.Provides;

/**
 * @author jeancarlos
 * @since 5/10/17
 */

@Component(modules = {BeerPresenterModule.class})
public interface BeerPresenterComponent {
    void inject(MainActivity mainActivity);
}
