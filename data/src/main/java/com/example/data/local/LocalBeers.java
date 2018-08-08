package com.example.data.local;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.domain.BeersDataSource;
import com.example.domain.models.Beer;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Concrete implementation of a {@link BeersLocalDataSource} and  as a db
 *
 * @author Jean Carlos
 * @since 5/10/17
 */

@Singleton
public class LocalBeers implements BeersDataSource.BeersLocalDataSource {

    private Realm mRealm;

    @Inject
    LocalBeers(@NonNull Context context) {
        Realm.init(context);
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public void fetchBeers(@NonNull BeersDataSource.FetchBeersCallback callback) {
        RealmQuery<Beer> query = mRealm.where(Beer.class);
        RealmResults<Beer> results = query.findAllSorted("name", Sort.ASCENDING);

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

    @Override
    public void saveFavoriteBeer(Beer beer) {
        insertOrUpdate(beer);
    }

    @Override
    public void removeFavoriteBeer(Beer beer) {
        insertOrUpdate(beer);
    }

    @Override
    public void getFavoriteBeers(FavoriteBeersCallback favoriteBeersCallback) {
        RealmQuery realmQuery = mRealm.where(Beer.class);
        realmQuery.equalTo("isFavorite", true);

        RealmResults<Beer> results = realmQuery.findAllSorted("name", Sort.ASCENDING);

        if (results.size() > 0)
            favoriteBeersCallback.onFavoriteBeersFetched(results);
        else
            favoriteBeersCallback.onFavoriteBeersNotFound();
    }

    /**
     * Insert or update a beer
     *
     * @param beer The beer
     */
    private void insertOrUpdate(Beer beer) {
        mRealm.beginTransaction();
        mRealm.insertOrUpdate(beer);
        mRealm.commitTransaction();
    }
}
