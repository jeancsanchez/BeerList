package br.com.jeancarlos.beerlist.features.beersdetail.presentation;

import br.com.jeancarlos.beerlist.base.BasePresenter;
import br.com.jeancarlos.beerlist.base.BaseView;
import br.com.jeancarlos.beerlist.features.beerslist.domain.model.Beer;

/**
 * This interface represents the contract between the view and the presenter.
 *
 * @author Jean Carlos
 * @since 5/16/17
 */

public interface BeersDetailContract {

    /**
     * Interface that represents the view
     */
    interface View extends BaseView<Presenter> {
        /**
         * Show the beer details
         */
        void showBeerDetails();

        /**
         * Show a icon for favorite a beer
         */
        void showFavoriteIcon();

        /**
         * Show a icon for disfavor a beer
         */
        void removeFavoriteIcon();
    }

    /**
     * Interface that represents the presenter
     */
    interface Presenter extends BasePresenter {

        /**
         * Favorites a beer
         *
         * @param beer Favorite beer
         */
        void favoriteBeer(Beer beer);

        /**
         * Disfavor Beer
         *
         * @param beer The beer
         */
        void disfavorBeer(Beer beer);
    }
}
