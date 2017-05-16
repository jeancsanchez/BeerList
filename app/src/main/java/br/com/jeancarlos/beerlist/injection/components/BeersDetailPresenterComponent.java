package br.com.jeancarlos.beerlist.injection.components;

import br.com.jeancarlos.beerlist.features.beersdetail.presentation.ui.BeersDetailActivity;
import br.com.jeancarlos.beerlist.injection.modules.BeersDetailPresenterModule;
import br.com.jeancarlos.beerlist.injection.scopes.PearActivity;
import dagger.Component;

/**
 * This class represents a Dagger BeerPresenter component
 *
 * @author Jean Carlos
 * @since 5/16/17
 */


@PearActivity
@Component(modules = {BeersDetailPresenterModule.class})
public interface BeersDetailPresenterComponent {

    void inject(BeersDetailActivity beersDetailActivity);
}
