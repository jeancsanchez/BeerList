package br.com.jeancarlos.beerlist.base;

import br.com.jeancarlos.beerlist.features.beerslist.domain.model.Beer;

/**
 * This interface represents a base of methods for all Views.
 *
 * @author Jean Carlos
 * @since 5/10/17.
 */

public interface BaseView<T> {

    /**
     * This interface is a callback for handle click on beer item
     */
    interface OnBeerItemClickedListener {

        /**
         * Callback for handle beer click
         *
         * @param beer Beer clicked
         */
        void beerClicked(Beer beer);
    }
}

