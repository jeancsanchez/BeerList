package br.com.jeancarlos.beerlist.features.favorites;

import java.util.List;

import br.com.jeancarlos.beerlist.base.BasePresenter;
import br.com.jeancarlos.beerlist.base.BaseView;
import com.example.domain.models.Beer;
import br.com.jeancarlos.beerlist.features.beerslist.BeersListContract;

/**
 * This interface represents the contract between the view and the presenter.
 *
 * @author Jean Carlos
 * @since 5/16/17
 */

public interface FavoritesContract {
    /**
     * Interface that represents the View
     */
    interface View extends BaseView<BeersListContract.Presenter> {

        /**
         * Show the list of favorite beers
         *
         * @param beers list of beers
         */
        void showFavorites(List<Beer> beers);

        /**
         * Inform view that there are no favorite beers
         */
        void onFavoritesNotFound();
    }

    /**
     * Interface that represents the presenter
     */
    interface Presenter extends BasePresenter {

    }
}
