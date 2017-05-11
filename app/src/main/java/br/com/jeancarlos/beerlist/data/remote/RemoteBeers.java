package br.com.jeancarlos.beerlist.data.remote;

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
public class RemoteBeers implements BeersDataSource {
    private Context context;

    @Inject
    RemoteBeers(@NonNull Context context){

    }

    @Override
    public void fetchBeers() {

    }
}
