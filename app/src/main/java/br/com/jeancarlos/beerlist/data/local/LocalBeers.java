package br.com.jeancarlos.beerlist.data.local;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.jeancarlos.beerlist.data.BeersDataSource;

/**
 * @author jeancarlos
 * @since 5/10/17
 */

@Singleton
public class LocalBeers implements BeersDataSource {
    private Context context;

    @Inject
    LocalBeers(@NonNull Context context){

    }

    @Override
    public void fetchBeers() {

    }
}
