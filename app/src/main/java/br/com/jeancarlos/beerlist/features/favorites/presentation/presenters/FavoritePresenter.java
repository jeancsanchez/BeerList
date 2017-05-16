package br.com.jeancarlos.beerlist.features.favorites.presentation.presenters;

import java.util.List;

import javax.inject.Inject;

import br.com.jeancarlos.beerlist.data.BeersDataSource;
import br.com.jeancarlos.beerlist.features.beerslist.domain.model.Beer;
import br.com.jeancarlos.beerlist.features.favorites.domain.ShowFavoritesUseCase;
import br.com.jeancarlos.beerlist.features.favorites.presentation.FavoritesContract;
import br.com.jeancarlos.beerlist.features.favorites.presentation.ui.FavoritesActivity;

/**
 * This class represents a presenter for {@link FavoritesActivity} and is a concrete implementation of
 * {@link FavoritesContract}
 *
 * @author Jean Carlos
 * @since 5/16/17
 */

public class FavoritePresenter implements FavoritesContract.Presenter {

    private final ShowFavoritesUseCase mShowFavoriteUseCase;
    private final FavoritesContract.View mView;

    @Inject
    public FavoritePresenter(ShowFavoritesUseCase showFavoritesUseCase,
                             FavoritesContract.View view) {
        this.mShowFavoriteUseCase = showFavoritesUseCase;
        this.mView = view;
    }

    @Override
    public void start() {
        mShowFavoriteUseCase.executeUseCase(new BeersDataSource.FavoriteBeersCallback() {
            @Override
            public void onFavoriteBeersFetched(List<Beer> favoriteBeers) {
                mView.showFavorites(favoriteBeers);
            }

            @Override
            public void onFavoriteBeersNotFound() {
                mView.onFavoritesNotFound();
            }
        });
    }
}
