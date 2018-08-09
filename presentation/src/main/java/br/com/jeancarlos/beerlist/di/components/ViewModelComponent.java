package br.com.jeancarlos.beerlist.di.components;

import br.com.jeancarlos.beerlist.features.BeerViewModel;
import dagger.Subcomponent;

/**
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 08/08/18.
 * Jesus is alive!
 */

@Subcomponent
public interface ViewModelComponent {

    void inject(BeerViewModel beerViewModel);
}
