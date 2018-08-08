package br.com.jeancarlos.beerlist.data.beerdetail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.data.BeersRepository;
import com.example.domain.usecases.FavoriteUseCase;
import com.example.domain.models.Beer;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * This class makes tests for {@link FavoriteUseCase}
 *
 * @author Jean Carlos
 * @since 5/16/17
 */

public class FavoriteUseCaseTest {
    @Mock
    private BeersRepository mBeerRepository;

    @InjectMocks
    private FavoriteUseCase mFavoriteUseCase;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void executeFavoriteBeerUseCase() {
        // Init a beer that is not favorite
        Beer beer = new Beer();
        beer.setFavorite(false);

        // Execute the use case
        mFavoriteUseCase.executeUseCase(beer);
        verify(mBeerRepository).saveFavoriteBeer(beer);
        verifyNoMoreInteractions(mBeerRepository);


        // Check if the beer is favorite now
        assertTrue(beer.isFavorite());
    }
}
