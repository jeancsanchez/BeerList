package br.com.jeancarlos.beerlist.data.beerlist;

import com.example.data.BeersRepositoryImpl;
import com.example.domain.BeersDataSource;
import com.example.domain.usecases.GetAllBeersUseCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * This class makes tests for {@link GetAllBeersUseCase}
 *
 * @author Jean Carlos
 * @since 5/15/17
 */

@RunWith(JUnit4.class)
public class GetAllBeersUseCaseTest {

    @Mock
    private BeersRepositoryImpl mBeerRepository;

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
        verifyNoMoreInteractions(mBeerRepository);
    }
}
