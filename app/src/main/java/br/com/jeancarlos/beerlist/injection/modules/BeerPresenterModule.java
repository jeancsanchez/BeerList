package br.com.jeancarlos.beerlist.injection.modules;

import br.com.jeancarlos.beerlist.features.beerslist.domain.usecases.GetAllBeersUseCase;
import br.com.jeancarlos.beerlist.features.beerslist.domain.usecases.GetBeerByNameUseCase;
import br.com.jeancarlos.beerlist.features.beerslist.presentation.BeersListContract;
import br.com.jeancarlos.beerlist.features.beerslist.presentation.presenters.BeersPresenter;
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
    private BeersListContract.View mView;
    private GetAllBeersUseCase mGetAllBeersUseCase;
    private GetBeerByNameUseCase mGetBeerByNameUseCase;

    public BeerPresenterModule(GetAllBeersUseCase getAllBeersUseCase,
                               GetBeerByNameUseCase getBeerByNameUseCase,
                               BeersListContract.View view) {
        this.mView = view;
        this.mGetAllBeersUseCase = getAllBeersUseCase;
        this.mGetBeerByNameUseCase = getBeerByNameUseCase;
    }

    @Provides
    BeersListContract.View providesBeerListView() {
        return mView;
    }

    @Provides
    GetAllBeersUseCase providesGetAllBeersUseCase() {
        return mGetAllBeersUseCase;
    }

    @Provides
    GetBeerByNameUseCase providesGetBeerByNameUseCase() {
        return mGetBeerByNameUseCase;
    }
}
