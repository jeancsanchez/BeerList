package br.com.jeancarlos.beerlist.beers.presentation.presenters;

import javax.inject.Inject;

import br.com.jeancarlos.beerlist.beers.presentation.BeersContract;
import br.com.jeancarlos.beerlist.data.BeersRepository;

/**
 * This class represents a presenter for
 * @author jeancarlos
 * @since 5/10/17
 */

public class BeerPresenter implements BeersContract.Presenter {

    @Inject
    BeersContract.View mView;

    @Override
    public void start() {

    }
}
