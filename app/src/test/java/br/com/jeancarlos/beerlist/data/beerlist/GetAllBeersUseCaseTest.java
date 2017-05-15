package br.com.jeancarlos.beerlist.data.beerlist;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.jeancarlos.beerlist.data.BeersDataSource;
import br.com.jeancarlos.beerlist.data.BeersRepository;
import br.com.jeancarlos.beerlist.features.beerslist.domain.usecases.GetAllBeersUseCase;

import static org.mockito.Mockito.verify;

/**
 * This class makes tests for {@link GetAllBeersUseCase}
 *
 * @author Jean Carlos
 * @since 5/15/17
 */

@RunWith(JUnit4.class)
public class GetAllBeersUseCaseTest {

    @Mock
    private BeersRepository mBeerRepository;

    @Mock
    private BeersDataSource.FetchBeersCallback mFetchBeersCallback;

    private GetAllBeersUseCase mGetAllBeersUseCase;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mGetAllBeersUseCase = new GetAllBeersUseCase(mBeerRepository);
    }

    @Test
    public void executeGetAllBeerUseCase() {
        mGetAllBeersUseCase.executeUseCase(mFetchBeersCallback);
        verify(mBeerRepository).fetchBeers(mFetchBeersCallback);
    }
}
