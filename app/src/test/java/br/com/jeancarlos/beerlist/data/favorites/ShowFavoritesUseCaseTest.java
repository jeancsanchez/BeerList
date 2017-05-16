package br.com.jeancarlos.beerlist.data.favorites;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.jeancarlos.beerlist.data.BeersDataSource;
import br.com.jeancarlos.beerlist.data.BeersRepository;
import br.com.jeancarlos.beerlist.features.favorites.domain.ShowFavoritesUseCase;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * This class makes tests for {@link ShowFavoritesUseCase}
 *
 * @author Jean Carlos
 * @since 5/16/17
 */

public class ShowFavoritesUseCaseTest {

    @Mock
    private BeersRepository mBeerRepository;

    @InjectMocks
    private ShowFavoritesUseCase mShowFavoritesUseCase;

    @Captor
    private ArgumentCaptor<BeersDataSource.FavoriteBeersCallback> mFavoriteBeersCallbackCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void executeShowFavoritesUseCase() {
        mShowFavoritesUseCase.executeUseCase(mFavoriteBeersCallbackCaptor.capture());

        verify(mBeerRepository).getFavoriteBeers(mFavoriteBeersCallbackCaptor.capture());
        verifyNoMoreInteractions(mBeerRepository);

    }
}
