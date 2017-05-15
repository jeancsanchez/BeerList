package br.com.jeancarlos.beerlist.data.local;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.jeancarlos.beerlist.features.beerslist.domain.model.Beer;
import br.com.jeancarlos.beerlist.data.BeersDataSource;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * @author jeancarlos
 * @since 5/10/17
 */

@Singleton
public class LocalBeers implements BeersDataSource {
    private Context context;
    private Realm mRealm;

    @Inject
    LocalBeers(@NonNull Context context) {
        Realm.init(context);
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public void fetchBeers(@NonNull FetchBeersCallback callback) {
        RealmQuery<Beer> query = mRealm.where(Beer.class);
        RealmResults<Beer> results = query.findAll();

        if (results.size() == 0) {
            callback.onBeersNotAvailable();

        } else {
            callback.onBeersFetched(results);
        }
    }

    @Override
    public void searchBeerByName(String query, @NonNull SearchBeerCallback callback) {
        // TODO: DO STUFF
    }


    @Override
    public void saveBeers(List<Beer> beers) {
        mRealm.beginTransaction();
        mRealm.insertOrUpdate(beers);
        mRealm.commitTransaction();
    }
}
