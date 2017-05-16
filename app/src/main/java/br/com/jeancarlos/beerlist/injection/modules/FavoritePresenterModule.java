package br.com.jeancarlos.beerlist.injection.modules;


import br.com.jeancarlos.beerlist.features.favorites.domain.ShowFavoritesUseCase;
import br.com.jeancarlos.beerlist.features.favorites.presentation.FavoritesContract;
import dagger.Module;
import dagger.Provides;

/**
 * This class represents a Dagger module. We use this to pass in the View dependency to the
 * {@link br.com.jeancarlos.beerlist.features.favorites.presentation.presenters.FavoritePresenter}.
 *
 * @author Jean Carlos
 * @since 5/16/17
 */

@Module
public class FavoritePresenterModule {

    private FavoritesContract.View mView;
    private ShowFavoritesUseCase mShowFavoritesUseCase;

    public FavoritePresenterModule(ShowFavoritesUseCase mShowFavoritesUseCase,
                                   FavoritesContract.View view) {
        this.mView = view;
        this.mShowFavoritesUseCase = mShowFavoritesUseCase;
    }

    @Provides
    FavoritesContract.View providesFavoritesView() {
        return mView;
    }

    @Provides
    ShowFavoritesUseCase providesShowFavoritesUseCase() {
        return mShowFavoritesUseCase;
    }

}
