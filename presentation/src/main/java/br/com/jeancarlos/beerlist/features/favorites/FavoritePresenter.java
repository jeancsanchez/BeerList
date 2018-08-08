package br.com.jeancarlos.beerlist.features.favorites;

import com.example.domain.BeersDataSource;
import com.example.domain.models.Beer;
import com.example.domain.usecases.ShowFavoritesUseCase;

import java.util.List;

import javax.inject.Inject;

import br.com.jeancarlos.beerlist.base.BaseView;

/**
 * This class represents a presenter for {@link FavoritesActivity} and is a concrete implementation of
 * {@link FavoritesContract}
 *
 * @author Jean Carlos
 * @since 5/16/17
 */

public class FavoritePresenter implements FavoritesContract.Presenter {
    private final ShowFavoritesUseCase mShowFavoriteUseCase;
    private FavoritesContract.View mView;

    @Inject
    public FavoritePresenter(ShowFavoritesUseCase showFavoritesUseCase) {
        this.mShowFavoriteUseCase = showFavoritesUseCase;
    }

    @Override
    public void start(BaseView view) {
        mView = (FavoritesContract.View) view;

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
