package br.com.jeancarlos.beerlist;

import android.app.Application;

import br.com.jeancarlos.beerlist.di.components.ActivityComponent;
import br.com.jeancarlos.beerlist.di.components.AppComponent;
import br.com.jeancarlos.beerlist.di.components.DaggerAppComponent;
import br.com.jeancarlos.beerlist.di.modules.ApplicationModule;

/**
 * This class represents the Application
 *
 * @author Jean Carlos
 * @since 5/10/17
 */

public class App extends Application {
    private static AppComponent appComponent;

    public static ActivityComponent getActivityComponent() {
        return appComponent.getActivityComponent();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initDaggerComponents();
    }

    private void initDaggerComponents() {
        appComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        appComponent.inject(this);
    }
}
