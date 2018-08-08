package br.com.jeancarlos.beerlist.features.beersdetail;

import javax.inject.Inject;

import br.com.jeancarlos.beerlist.base.BaseView;
import br.com.jeancarlos.beerlist.di.scopes.PearActivity;
import com.example.domain.usecases.DisfavorUseCase;
import com.example.domain.usecases.FavoriteUseCase;
import com.example.domain.models.Beer;

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
