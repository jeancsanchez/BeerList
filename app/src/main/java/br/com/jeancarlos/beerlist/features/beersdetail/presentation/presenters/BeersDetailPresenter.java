package br.com.jeancarlos.beerlist.features.beersdetail.presentation.presenters;

import javax.inject.Inject;

import br.com.jeancarlos.beerlist.base.BaseView;
import br.com.jeancarlos.beerlist.di.scopes.PearActivity;
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

@PearActivity
public class BeersDetailPresenter implements BeersDetailContract.Presenter {


    private final FavoriteUseCase mFavoriteUseCase;
    private final DisfavorUseCase mDisfavorUseCase;
    private BeersDetailContract.View mView;

    @Inject
    BeersDetailPresenter(FavoriteUseCase favoriteUseCase, DisfavorUseCase disfavorUseCase) {
        this.mFavoriteUseCase = favoriteUseCase;
        this.mDisfavorUseCase = disfavorUseCase;
    }


    @Override
    public void start(BaseView view) {
        mView = (BeersDetailContract.View) view;
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
