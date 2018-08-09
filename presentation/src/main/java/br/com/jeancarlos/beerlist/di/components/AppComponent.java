package br.com.jeancarlos.beerlist.di.components;

import javax.inject.Singleton;

import br.com.jeancarlos.beerlist.App;
import br.com.jeancarlos.beerlist.di.modules.ApplicationModule;
import br.com.jeancarlos.beerlist.di.modules.RepositoryModule;
import dagger.Component;

/**
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 08/08/18.
 * Jesus is alive!
 */

@Singleton
@Component(modules = {ApplicationModule.class, RepositoryModule.class})
public interface AppComponent {

    void inject(App app);

    ActivityComponent getActivityComponent();

    ViewModelComponent getViewModelComponent();
}
