package br.com.jeancarlos.beerlist;

import android.app.Application;

import br.com.jeancarlos.beerlist.beers.presentation.presenters.BeerPresenterComponent;
import br.com.jeancarlos.beerlist.beers.presentation.presenters.DaggerBeerPresenterComponent;

/**
 * @author jeancarlos
 * @since 5/10/17
 */

public class App extends Application {
    private static BeerPresenterComponent mBeerPresenterComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initDaggerComponents();
    }

    private void initDaggerComponents() {
        mBeerPresenterComponent = DaggerBeerPresenterComponent
                .builder()
                .build();
    }


    public static BeerPresenterComponent getBeerPresenterComponent() {
        return mBeerPresenterComponent;
    }

}
