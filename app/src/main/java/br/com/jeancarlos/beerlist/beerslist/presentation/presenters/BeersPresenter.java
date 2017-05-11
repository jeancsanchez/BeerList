package br.com.jeancarlos.beerlist.beerslist.presentation.presenters;

import javax.inject.Inject;

import br.com.jeancarlos.beerlist.beerslist.presentation.BeersListContract;
import br.com.jeancarlos.beerlist.data.BeersRepository;

/**
 * This class represents a presenter for
 *
 * @author jeancarlos
 * @since 5/10/17
 */

public class BeersPresenter implements BeersListContract.Presenter {

    private BeersRepository mBeerRepository;
    private BeersListContract.View mView;

    @Inject
    BeersPresenter(BeersRepository beersRepository, BeersListContract.View view) {
        mBeerRepository = beersRepository;
        mView = view;
    }


    @Override
    public void start() {

    }
}
