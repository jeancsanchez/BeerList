package com.example.data.remote;

import android.support.annotation.NonNull;

import com.example.data.local.BeerEntity;
import com.example.domain.BeersDataSource;
import com.example.domain.models.Beer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Concrete implementation of a {@link BeersDataSource} as a rest service.
 *
 * @author Jean Carlos
 * @since 5/10/17
 */

@Singleton
public class RemoteBeers implements BeersDataSource {

    private BeerApi mBeerApi;

    @Inject
    RemoteBeers() {
        mBeerApi = BeerApi.Creator.newRetrofitService();
    }

    @Override
    public void fetchBeers(@NonNull final BeersDataSource.FetchBeersCallback callback) {
        mBeerApi.getBeers()
                .enqueue(new Callback<List<BeerEntity>>() {
                    @Override
                    public void onResponse(Call<List<BeerEntity>> call, Response<List<BeerEntity>> response) {
                        if (response.body() != null) {
                            List<Beer> beerList = new ArrayList<>();

                            for (BeerEntity beerEntity : response.body()) {
                                beerList.add(beerEntity.mapOut());
                            }

                            callback.onBeersFetched(beerList);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<BeerEntity>> call, Throwable t) {
                        callback.onBeersFetchError();
                    }
                });
    }

    @Override
    public void searchBeerByName(String query, @NonNull final SearchBeerCallback callback) {
        mBeerApi.searchBeerByName(query)
                .enqueue(new Callback<List<BeerEntity>>() {
                    @Override
                    public void onResponse(Call<List<BeerEntity>> call, Response<List<BeerEntity>> response) {
                        if (response.body() != null) {
                            List<Beer> beerList = new ArrayList<>();

                            for (BeerEntity beerEntity : response.body()) {
                                beerList.add(beerEntity.mapOut());
                            }

                            callback.onSearchBeerSuccess(beerList);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<BeerEntity>> call, Throwable t) {
                        callback.onSearchBeerFailure();
                    }
                });
    }
}
