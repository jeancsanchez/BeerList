package br.com.jeancarlos.beerlist.data.remote;

import java.util.List;

import br.com.jeancarlos.beerlist.beerslist.domain.model.Beer;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Jean Carlos
 * @since 5/12/17
 */

public interface RetrofitService {
    String ENDPOINT = "https://api.punkapi.com/v2/";

    @GET("beers")
    Call<List<Beer>> getBeers();

    @GET("beers")
    Call<List<Beer>> searchBeerByName(@Query("beer_name") String query);

    /**
     * This class helps set up a new Retrofit Service
     */
    class Creator {
        private static RetrofitService mRetrofitService;

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
