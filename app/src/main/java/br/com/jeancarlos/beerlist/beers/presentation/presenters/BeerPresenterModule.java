package br.com.jeancarlos.beerlist.beers.presentation.presenters;

import dagger.Module;
import dagger.Provides;

/**
 * This class represents a Dagger module. We use this to pass in the View dependency to the
 * {@link BeerPresenter}.
 *
 * @author Jean Carlos
 * @since 5/10/17
 */

@Module
public class BeerPresenterModule {

    @Provides
    BeerPresenter providesBeerPresenter() {
        return new BeerPresenter();
    }
}
