package br.com.jeancarlos.beerlist.data.beerdetail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.jeancarlos.beerlist.data.BeersRepository;
import br.com.jeancarlos.beerlist.features.beersdetail.domain.DisfavorUseCase;
import br.com.jeancarlos.beerlist.features.beerslist.domain.model.Beer;

import static junit.framework.Assert.assertFalse;
import static org.mockito.Mockito.verify;

/**
 * This class makes tests for {@link DisfavorUseCase}
 *
 * @author Jean Carlos
 * @since 5/16/17
 */

public class DisfavorUseCaseTest {
    @Mock
    private BeersRepository mBeerRepository;

    @InjectMocks
    private DisfavorUseCase mDisfavorUseCase;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void executeFavoriteBeerUseCase() {
        // Init a favorite beer
        Beer beer = new Beer();
        beer.setFavorite(true);

        // Execute the use case
        mDisfavorUseCase.executeUseCase(beer);
        verify(mBeerRepository).removeFavoriteBeer(beer);

        // Check if the beer is not favorite now
        assertFalse(beer.isFavorite());
    }
}
