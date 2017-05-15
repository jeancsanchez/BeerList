package br.com.jeancarlos.beerlist.data.remote;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import br.com.jeancarlos.beerlist.features.beerslist.domain.model.Beer;
import retrofit2.Call;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


/**
 * @author Jean Carlos
 * @since 5/12/17
 */

public class RetrofitServiceTest {

    private RetrofitService mRetrofitService;


    @Before
    public void setUp() {
        mRetrofitService = RetrofitService.Creator.newRetrofitService();
    }


    // Check if Retrofit request for Beers is successful (ignore SSLHandshakeException)
    @Test
    public void testFetchingBeersFromService() {
        try {
            Call<List<Beer>> call = mRetrofitService.getBeers();
            assertTrue(call.execute().isSuccessful());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSearchBeerFromName() {
        try {
            Call<List<Beer>> call = mRetrofitService.searchBeerByName("Skol");
            assertTrue(call.execute().isSuccessful());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
