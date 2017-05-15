package br.com.jeancarlos.beerlist.features.beerslist.presentation;

import java.util.List;

import br.com.jeancarlos.beerlist.base.BasePresenter;
import br.com.jeancarlos.beerlist.base.BaseView;
import br.com.jeancarlos.beerlist.features.beerslist.domain.model.Beer;

/**
 * This interface represents the contract between the view and the presenter.
 *
 * @author Jean Carlos
 * @since 5/10/17.
 */

public interface BeersListContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showBeers(List<Beer> beers);

        void showBeersSearchResult(List<Beer> beers);

        void onBeersUpdate(List<Beer> beers);

        void showConnectionFailedError();

        void showDataNotAvailable();

        interface OnBeerItemClickedListener {

            void beerClicked(Beer beer);
        }
    }

    interface Presenter extends BasePresenter {

        void getBeerByName(String query);

        void refreshBeers();
    }
}