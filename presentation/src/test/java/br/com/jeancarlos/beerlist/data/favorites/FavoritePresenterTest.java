package br.com.jeancarlos.beerlist.data.favorites;

import com.example.domain.models.Beer;
import com.example.domain.usecases.ShowFavoritesUseCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import br.com.jeancarlos.beerlist.features.favorites.FavoritePresenter;
import br.com.jeancarlos.beerlist.features.favorites.FavoritesContract;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

/**
 * This class makes tests for {@link FavoritePresenter}
 *
 * @author Jean Carlos
 * @since 5/16/17
 */


@RunWith(JUnit4.class)
public class FavoritePresenterTest {

    private static List<Beer> BEERS;

    @Mock
    ShowFavoritesUseCase mShowFavoritesUseCase;

    @Mock
    FavoritesContract.View mBeerContractView;
    @InjectMocks
    FavoritePresenter mFavoriteBeersPresenter;
    @Captor
    private ArgumentCaptor<BeersDataSource.FavoriteBeersCallback> mFavoriteBeersCallbackCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        BEERS = new ArrayList<>();
        BEERS.add(new Beer(1));
        BEERS.add(new Beer(2));
        BEERS.add(new Beer(3));
    }

    @Test
    public void showAllFavoriteBeers() {
        mFavoriteBeersPresenter.start();

        // Executes the use case
        verify(mShowFavoritesUseCase).executeUseCase(mFavoriteBeersCallbackCaptor.capture());
        mFavoriteBeersCallbackCaptor.getValue().onFavoriteBeersFetched(BEERS);

        // Shows the results on the view
        ArgumentCaptor<List> showBeersArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(mBeerContractView).showFavorites(showBeersArgumentCaptor.capture());

        // Verify if is the same list
        assertTrue(showBeersArgumentCaptor.getValue().size() == BEERS.size());
    }
}
