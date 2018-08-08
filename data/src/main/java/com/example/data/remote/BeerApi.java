package com.example.data.remote;

import com.example.domain.models.Beer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Interface that defines all endpoints and provides methods for querying on API
 *
 * @author Jean Carlos
 * @since 5/12/17
 */

public interface BeerApi {
    String ENDPOINT = "https://api.punkapi.com/v2/";

    /**
     * Returns all beers
     *
     * @return Callback with a list of beers
     * @see <a href="https://punkapi.com/documentation/v2">API Documentation</a>
     */
    @GET("beers")
    Call<List<Beer>> getBeers();


    /**
     * Returns all beers matching the supplied name (this will match partial strings as well
     * so e.g punk will return Punk IPA), if you need to add spaces just add an underscore (_).
     *
     * @param query The query String
     * @return Callback with a list of beers
     * @see <a href="https://punkapi.com/documentation/v2">API Documentation</a>
     */
    @GET("beers")
    Call<List<Beer>> searchBeerByName(@Query("beer_name") String query);


    /**
     * This class helps set up a new Retrofit Service
     */
    class Creator {
        private static BeerApi mBeerApi;

        /**
         * Return a single instance of {@link BeerApi}
         *
         * @return A new instance of {@link BeerApi}
         */
        public static BeerApi newRetrofitService() {
            if (mBeerApi == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(ENDPOINT)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                mBeerApi = retrofit.create(BeerApi.class);
            }

            return mBeerApi;
        }
    }
}
