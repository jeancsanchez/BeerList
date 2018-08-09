package br.com.jeancarlos.beerlist.data.beerlist;

import com.example.domain.BeersDataSource;
import com.example.domain.models.Beer;
import com.example.domain.usecases.GetAllBeersUseCase;
import com.example.domain.usecases.GetBeerByNameUseCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import br.com.jeancarlos.beerlist.features.BeerViewModel;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 * This class makes tests for {@link BeerViewModel}
 *
 * @author Jean Carlos
 * @since 5/15/17
 */


@RunWith(JUnit4.class)
public class BeerPresenterTest {

    private static List<Beer> BEERS;

    @Captor
    private ArgumentCaptor<BeersDataSource.FetchBeersCallback> mFetchBeersCallbackCaptor;

    @Captor
    private ArgumentCaptor<BeersDataSource.SearchBeerCallback> mSearchBeerCallbackCaptor;

    @Mock
    GetAllBeersUseCase mGetAllBeersUseCase;

    @Mock
    GetBeerByNameUseCase mGetBeerByNameUseCase;

    BeerViewModel mBeersViewModel;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        BEERS = new ArrayList<>();
        BEERS.add(new Beer(1));
        BEERS.add(new Beer(2));
        BEERS.add(new Beer(3));

        mBeersViewModel = new BeerViewModel();
    }

    @Test
    public void getAllBeers() {
        mBeersViewModel.getBeers();

        verify(mGetAllBeersUseCase).executeUseCase(mFetchBeersCallbackCaptor.capture());
        mFetchBeersCallbackCaptor.getValue().onBeersFetched(BEERS);

        verify(mBeersViewModel.mBeers).setValue(eq(BEERS));
        verify(mBeersViewModel.error).setValue(eq(false));
    }

//    @Test
//    public void getBeerByNameFromRepositoryAndLoadIntoView() {
//        String query = "skol";
//        mBeersViewModel.getBeerByName(query);
//
//        // Show the swipe refresh
//        verify(mBeerContractView).setLoadingIndicator(true);
//
//        // Executes the user case
//        verify(mGetBeerByNameUseCase).executeUseCase(eq(query), mSearchBeerCallbackCaptor.capture());
//        mSearchBeerCallbackCaptor.getValue().onSearchBeerSuccess(BEERS);
//
//        // Hides the swipe refresh
//        verify(mBeerContractView).setLoadingIndicator(false);
//
//        // Shows the results on the view
//        ArgumentCaptor<List> showBeersArgumentCaptor = ArgumentCaptor.forClass(List.class);
//        verify(mBeerContractView).showBeersSearchResult(showBeersArgumentCaptor.capture());
//
//        // Verify if is the same list
//        assertTrue(showBeersArgumentCaptor.getValue().size() == BEERS.size());
//    }
//
//    @Test
//    public void refreshBeersFromRepositoryAndLoadIntoView() {
//        mBeersViewModel.refreshBeers();
//
//        // Show the swipe refresh
//        verify(mBeerContractView).setLoadingIndicator(true);
//
//        // Executes the user case
//        verify(mGetAllBeersUseCase).executeUseCase(mFetchBeersCallbackCaptor.capture());
//        mFetchBeersCallbackCaptor.getValue().onBeersFetched(BEERS);
//
//        // Hides the swipe refresh
//        verify(mBeerContractView).setLoadingIndicator(false);
//
//        // Shows the results on the view
//        ArgumentCaptor<List> showBeersArgumentCaptor = ArgumentCaptor.forClass(List.class);
//        verify(mBeerContractView).onBeersUpdate(showBeersArgumentCaptor.capture());
//
//        // Verify if is the same list
//        assertTrue(showBeersArgumentCaptor.getValue().size() == BEERS.size());
//    }
}
