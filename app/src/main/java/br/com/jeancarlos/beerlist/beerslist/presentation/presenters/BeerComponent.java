package br.com.jeancarlos.beerlist.beerslist.presentation.presenters;

import br.com.jeancarlos.beerlist.beerslist.presentation.ui.MainActivity;
import br.com.jeancarlos.beerlist.data.BeerRepositoryComponent;
import br.com.jeancarlos.beerlist.util.PearActivity;
import dagger.Component;

/**
 * @author jeancarlos
 * @since 5/10/17
 */

@PearActivity
@Component(dependencies = {BeerRepositoryComponent.class}, modules = {BeerPresenterModule.class})
public interface BeerComponent {
    void inject(MainActivity mainActivity);
}
