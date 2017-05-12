package br.com.jeancarlos.beerlist.data.remote;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


/**
 * @author jeancarlos
 * @since 5/12/17
 */

public class RetrofitServiceTest {

    private RetrofitService mRetrofitService;


    @Before
    public void setUp() {
        mRetrofitService = RetrofitService.Creator.newRetrofitService();
    }


    // Check if Retrofit request for Beers is successful
    @Test
    public void test_fetching_beers_from_service() {
        // TODO: FINALIZE THIS FUNCTION CONTENT
//        try {
//            Call<List<Beer>> call = mRetrofitService.getBeers();
//            assertTrue(call.execute().isSuccessful());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
