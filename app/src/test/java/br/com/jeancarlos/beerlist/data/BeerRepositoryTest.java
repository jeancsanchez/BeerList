package br.com.jeancarlos.beerlist.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.jeancarlos.beerlist.data.local.LocalBeers;
import br.com.jeancarlos.beerlist.data.remote.RemoteBeers;

import static org.mockito.Mockito.verify;

/**
 * This class makes tests for {@link BeersRepository}
 *
 * @author Jean Carlos
 * @since 5/12/17
 */


@RunWith(JUnit4.class)
public class BeerRepositoryTest {

    @Captor
    private ArgumentCaptor<BeersDataSource.FetchBeersCallback> mFetchBeerCallbackCaptor;

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
    public void fetchBeers() {
        mBeersRepository.fetchBeers(mFetchBeerCallbackCaptor.capture());

        // Call local beers first
        verify(mLocalBeers).fetchBeers(mFetchBeerCallbackCaptor.capture());

        // Update from rest service
        verify(mRemoteBeers).fetchBeers(mFetchBeerCallbackCaptor.capture());
    }
}
