package br.com.jeancarlos.beerlist.features.beerslist;

/**
 * This interface represents the contract between the view and the presenter.
 *
 * @author Jean Carlos
 * @since 5/10/17.
 */

public interface FavoriteCallback {

    /**
     * Callback for click on favorites item
     */
    void favoritesClicked();

}
