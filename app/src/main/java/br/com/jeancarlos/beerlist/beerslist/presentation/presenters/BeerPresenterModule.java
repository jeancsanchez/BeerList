package br.com.jeancarlos.beerlist.beerslist.presentation.presenters;

import br.com.jeancarlos.beerlist.beerslist.presentation.BeersContract;
import dagger.Module;
import dagger.Provides;

/**
 * This class represents a Dagger module. We use this to pass in the View dependency to the
 * {@link BeersPresenter}.
 *
 * @author Jean Carlos
 * @since 5/10/17
 */

@Module
public class BeerPresenterModule {
    private final BeersContract.View mView;

    public BeerPresenterModule(BeersContract.View view) {
        this.mView = view;
    }

    @Provides
    BeersPresenter providesBeerPresenter() {
        return new BeersPresenter(null, mView);
    }
}
