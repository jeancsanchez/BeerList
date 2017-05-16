package br.com.jeancarlos.beerlist.injection.components;

import br.com.jeancarlos.beerlist.features.favorites.presentation.ui.FavoritesActivity;
import br.com.jeancarlos.beerlist.injection.modules.FavoritePresenterModule;
import br.com.jeancarlos.beerlist.injection.scopes.PearActivity;
import dagger.Component;

/**
 * This class represents a Dagger FavoritePresenter component
 *
 * @author Jean Carlos
 * @since 5/16/17
 */

@PearActivity
@Component(modules = {FavoritePresenterModule.class})
public interface FavoritePresenterComponent {

    void inject(FavoritesActivity favoritesActivity);
}
