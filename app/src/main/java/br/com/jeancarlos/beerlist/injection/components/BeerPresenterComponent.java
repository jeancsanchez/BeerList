package br.com.jeancarlos.beerlist.injection.components;

import br.com.jeancarlos.beerlist.features.beerslist.presentation.ui.MainActivity;
import br.com.jeancarlos.beerlist.injection.modules.BeerPresenterModule;
import br.com.jeancarlos.beerlist.injection.scopes.PearActivity;
import dagger.Component;

/**
 * This class represents a Dagger BeerPresenter component
 *
 * @author Jean Carlos
 * @since 5/10/17
 */

@PearActivity
@Component(modules = {BeerPresenterModule.class})
public interface BeerPresenterComponent {

    void inject(MainActivity mainActivity);
}
