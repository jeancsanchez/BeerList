package br.com.jeancarlos.beerlist.data.local;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.jeancarlos.beerlist.data.BeersDataSource;
import br.com.jeancarlos.beerlist.features.beerslist.domain.model.Beer;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Concrete implementation of a {@link BeersDataSource} as a db
 *
 * @author Jean Carlos
 * @since 5/10/17
 */

@Singleton
public class LocalBeers implements BeersDataSource, BeersDataSource.OnSaveBeers {
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
        RealmQuery realmQuery = mRealm.where(Beer.class);
        realmQuery.contains("name", query);
        RealmResults<Beer> results = realmQuery.findAll();

        callback.onSearchBeerSuccess(results);
    }


    @Override
    public void saveBeers(List<Beer> beers) {
        mRealm.beginTransaction();
        mRealm.insertOrUpdate(beers);
        mRealm.commitTransaction();
    }
}
