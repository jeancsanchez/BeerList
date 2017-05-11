package br.com.jeancarlos.beerlist.beerslist.presentation.presenters;

import br.com.jeancarlos.beerlist.beerslist.presentation.BeersListContract;
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
    private final BeersListContract.View mView;

    public BeerPresenterModule(BeersListContract.View view) {
        this.mView = view;
    }

    @Provides
    BeersListContract.View providesBeerListView() {
        return mView;
    }
}
