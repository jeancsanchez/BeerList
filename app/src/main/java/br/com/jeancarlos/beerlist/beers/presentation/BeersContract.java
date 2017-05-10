package br.com.jeancarlos.beerlist.beers.presentation;

import java.util.List;

import br.com.jeancarlos.beerlist.BasePresenter;
import br.com.jeancarlos.beerlist.BaseView;
import br.com.jeancarlos.beerlist.beers.domain.model.Beer;

/**
 * This interface represents the contract between the view and the presenter.
 *
 * @author Jean Carlos
 * @since 5/10/17.
 */

public interface BeersContract {
    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(boolean active);

        void showBeers(List<Beer> beers);

    }

    interface Presenter extends BasePresenter {

    }
}
