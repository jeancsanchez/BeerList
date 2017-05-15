package br.com.jeancarlos.beerlist.data.remote;

import java.util.List;

import br.com.jeancarlos.beerlist.features.beerslist.domain.model.Beer;
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

public interface RetrofitService {
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
        private static RetrofitService mRetrofitService;

        /**
         * Return a single instance of {@link RetrofitService}
         *
         * @return A new instance of {@link RetrofitService}
         */
        public static RetrofitService newRetrofitService() {
            if (mRetrofitService == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(ENDPOINT)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                mRetrofitService = retrofit.create(RetrofitService.class);
            }

            return mRetrofitService;
        }
    }
}
