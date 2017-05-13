package br.com.jeancarlos.beerlist.beerslist.presentation.ui;

import br.com.jeancarlos.beerlist.beerslist.domain.model.Beer;

/**
 * @author Jean Carlos
 * @since 5/12/17
 */

public interface OnBeerItemClickedListener {

    void beerClicked(Beer beer);
}
