package br.com.jeancarlos.beerlist.features.beerslist;

import java.util.List;

import br.com.jeancarlos.beerlist.base.BasePresenter;
import br.com.jeancarlos.beerlist.base.BaseView;
import com.example.domain.models.Beer;

/**
 * This interface represents the contract between the view and the presenter.
 *
 * @author Jean Carlos
 * @since 5/10/17.
 */

public interface BeersListContract {

    /**
     * Interface that represents the View
     */
    interface View extends BaseView<Presenter> {

        /**
         * Shows or not the loading indicator
         *
         * @param active boolean for activate or deactivate indicator
         */
        void setLoadingIndicator(boolean active);

        /**
         * Show beers list
         *
         * @param beers list of beers
         */
        void showBeers(List<Beer> beers);


        /**
         * Show search beers result
         *
         * @param beers list of beers
         */
        void showBeersSearchResult(List<Beer> beers);


        /**
         * Callback for updating list beers from repository
         *
         * @param beers
         */
        void onBeersUpdate(List<Beer> beers);


        /**
         * Show message for failed connections
         */
        void showConnectionFailedError();


        /**
         * Show message for data not available
         */
        void showDataNotAvailable();


        /**
         * This interface is a callback for handle click on beer item
         */
        interface OnFavoritesItemClickedListener {

            /**
             * Callback for click on favorites item
             */
            void favoritesClicked();
        }
    }

    /**
     * Interface that represents the presenter
     */
    interface Presenter extends BasePresenter {

        /**
         * Get beer by name
         *
         * @param query Beer's name
         */
        void getBeerByName(String query);

        /**
         * Refresh beers
         */
        void refreshBeers();
    }
}
