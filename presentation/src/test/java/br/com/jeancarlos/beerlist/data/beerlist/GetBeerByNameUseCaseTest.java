package br.com.jeancarlos.beerlist.data.beerlist;

import com.example.data.BeersRepositoryImpl;
import com.example.domain.usecases.GetBeerByNameUseCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * This class makes tests for {@link GetBeerByNameUseCase}
 *
 * @author Jean Carlos
 * @since 5/15/17
 */

public class GetBeerByNameUseCaseTest {
    @Mock
    private BeersRepositoryImpl mBeerRepository;

    @Mock
    private BeersDataSource.SearchBeerCallback mSearchBeerCallback;

    private GetBeerByNameUseCase mGetBeerByNameUseCase;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mGetBeerByNameUseCase = new GetBeerByNameUseCase(mBeerRepository);
    }

    @Test
    public void executeGetAllBeerUseCase() {
        String query = "Skol";
        mGetBeerByNameUseCase.executeUseCase(query, mSearchBeerCallback);

        verify(mBeerRepository).searchBeerByName(query, mSearchBeerCallback);
        verifyNoMoreInteractions(mBeerRepository);
    }
}
