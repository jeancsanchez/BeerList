package br.com.jeancarlos.beerlist.di.components;

import br.com.jeancarlos.beerlist.di.scopes.PearActivity;
import br.com.jeancarlos.beerlist.features.beersdetail.presentation.ui.BeersDetailActivity;
import br.com.jeancarlos.beerlist.features.beerslist.presentation.ui.MainActivity;
import br.com.jeancarlos.beerlist.features.favorites.presentation.ui.FavoritesActivity;
import dagger.Subcomponent;

/**
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 08/08/18.
 * Jesus is alive!
 */

@PearActivity
@Subcomponent
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(FavoritesActivity favoritesActivity);

    void inject(BeersDetailActivity beersDetailActivity);
}
