package br.com.jeancarlos.beerlist.data.remote;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.jeancarlos.beerlist.beerslist.domain.model.Beer;
import br.com.jeancarlos.beerlist.data.BeersDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author jeancarlos
 * @since 5/10/17
 */

@Singleton
public class RemoteBeers implements BeersDataSource {

    private Context context;
    private RetrofitService mRetrofitService;

    @Inject
    RemoteBeers(@NonNull Context context) {
        mRetrofitService = RetrofitService.Creator.newRetrofitService();
    }

    @Override
    public void fetchBeers(@NonNull final FetchBeersCallback callback) {
        mRetrofitService.getBeers()
                .enqueue(new Callback<List<Beer>>() {
                    @Override
                    public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {
                        if (response.body() != null) {
                            callback.onBeersFetched(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Beer>> call, Throwable t) {
                        callback.onBeersFetchError();
                    }
                });
    }

    @Override
    public void searchBeerByName(String query, @NonNull final SearchBeerCallback callback) {
        query.replace(" ", "_");

        mRetrofitService.searchBeerByName(query)
                .enqueue(new Callback<List<Beer>>() {
                    @Override
                    public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {
                        if (response.body() != null) {
                            callback.onSearchBeerSuccess(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Beer>> call, Throwable t) {
                        callback.onSearchBeerFailure();
                    }
                });
    }

    @Override
    public void saveBeers(List<Beer> beers) {

    }
}
