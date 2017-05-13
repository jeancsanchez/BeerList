package br.com.jeancarlos.beerlist.beerslist.presentation;

import java.util.List;

import br.com.jeancarlos.beerlist.base.BasePresenter;
import br.com.jeancarlos.beerlist.base.BaseView;
import br.com.jeancarlos.beerlist.beerslist.domain.model.Beer;

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

        void showConnectionFailedError();
    }

    interface Presenter extends BasePresenter {

    }
}
