package br.com.jeancarlos.beerlist.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.jeancarlos.beerlist.data.local.LocalBeers;
import br.com.jeancarlos.beerlist.data.remote.RemoteBeers;



import static org.mockito.Mockito.verify;

/**
 * @author Jean Carlos
 * @since 5/12/17
 */


@RunWith(JUnit4.class)
public class BeerRepositoryTest {

    @Mock
    BeersDataSource.FetchBeersCallback fetchBeersCallback;
    @Mock
    private RemoteBeers mRemoteBeers;
    @Mock
    private LocalBeers mLocalBeers;
    private BeersRepository mBeersRepository;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mBeersRepository = new BeersRepository(mRemoteBeers, mLocalBeers);
    }


    @Test
    public void fetch_beers_from_rest_service() {
        mBeersRepository.fetchBeers(fetchBeersCallback);

        verify(mRemoteBeers).fetchBeers(fetchBeersCallback);
    }


    @Test
    public void fetch_beers_locally() {
        mBeersRepository.fetchBeers(fetchBeersCallback);

        verify(mLocalBeers).fetchBeers(fetchBeersCallback);
    }
}
