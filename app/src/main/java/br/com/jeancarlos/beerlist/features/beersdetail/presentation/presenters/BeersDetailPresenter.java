package br.com.jeancarlos.beerlist.features.beersdetail.presentation.presenters;

import javax.inject.Inject;

import br.com.jeancarlos.beerlist.features.beersdetail.domain.DisfavorUseCase;
import br.com.jeancarlos.beerlist.features.beersdetail.domain.FavoriteUseCase;
import br.com.jeancarlos.beerlist.features.beersdetail.presentation.BeersDetailContract;
import br.com.jeancarlos.beerlist.features.beersdetail.presentation.ui.BeersDetailActivity;
import br.com.jeancarlos.beerlist.features.beerslist.domain.model.Beer;

/**
 * This class represents a presenter for {@link BeersDetailActivity} and is a concrete implementation of
 * {@link BeersDetailContract}
 *
 * @author Jean Carlos
 * @since 5/16/17
 */

public class BeersDetailPresenter implements BeersDetailContract.Presenter {


    private final FavoriteUseCase mFavoriteUseCase;
    private final DisfavorUseCase mDisfavorUseCase;
    private final BeersDetailContract.View mView;

    @Inject
    BeersDetailPresenter(FavoriteUseCase favoriteUseCase,
                         DisfavorUseCase disfavorUseCase,
                         BeersDetailContract.View view) {
        this.mFavoriteUseCase = favoriteUseCase;
        this.mDisfavorUseCase = disfavorUseCase;
        this.mView = view;
    }


    @Override
    public void start() {
        mView.showBeerDetails();
    }

    @Override
    public void favoriteBeer(Beer beer) {
        mFavoriteUseCase.executeUseCase(beer);
    }

    @Override
    public void disfavorBeer(Beer beer) {
        mDisfavorUseCase.executeUseCase(beer);
    }
}
