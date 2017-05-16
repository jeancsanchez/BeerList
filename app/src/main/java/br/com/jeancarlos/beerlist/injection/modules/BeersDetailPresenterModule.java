package br.com.jeancarlos.beerlist.injection.modules;

import br.com.jeancarlos.beerlist.features.beersdetail.domain.DisfavorUseCase;
import br.com.jeancarlos.beerlist.features.beersdetail.domain.FavoriteUseCase;
import br.com.jeancarlos.beerlist.features.beersdetail.presentation.BeersDetailContract;
import dagger.Module;
import dagger.Provides;

/**
 * This is used by Dagger to inject the required arguments into the
 * {@link br.com.jeancarlos.beerlist.features.beersdetail.presentation.presenters.BeersDetailPresenter}.
 *
 * @author Jean Carlos
 * @since 5/16/17
 */

@Module
public class BeersDetailPresenterModule {
    private BeersDetailContract.View mView;
    private FavoriteUseCase mFavoritesUseCase;
    private DisfavorUseCase mDisfavorUseCase;

    public BeersDetailPresenterModule(FavoriteUseCase favoritesUseCase,
                                      DisfavorUseCase disfavorUseCase,
                                      BeersDetailContract.View view) {
        this.mView = view;
        this.mFavoritesUseCase = favoritesUseCase;
        this.mDisfavorUseCase = disfavorUseCase;
    }

    @Provides
    BeersDetailContract.View providesDetailsView() {
        return mView;
    }

    @Provides
    FavoriteUseCase providesFavoriteUseCase() {
        return mFavoritesUseCase;
    }


    @Provides
    DisfavorUseCase providesDisfavorUseCase() {
        return mDisfavorUseCase;
    }
}
