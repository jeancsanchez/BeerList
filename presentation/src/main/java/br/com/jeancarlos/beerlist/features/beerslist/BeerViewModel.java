package br.com.jeancarlos.beerlist.features.beerslist;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.domain.BeersDataSource;
import com.example.domain.models.Beer;
import com.example.domain.usecases.GetAllBeersUseCase;
import com.example.domain.usecases.GetBeerByNameUseCase;

import java.util.List;

import javax.inject.Inject;

import br.com.jeancarlos.beerlist.App;

/**
 * This class represents...
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 08/08/18.
 * Jesus is alive!
 */

public class BeerViewModel extends ViewModel {

    public MutableLiveData<List<Beer>> mBeers = new MutableLiveData<>();
    public MutableLiveData<Boolean> error = new MutableLiveData<>();

    @Inject
    GetAllBeersUseCase mGetAllBeersUserCase;

    @Inject
    GetBeerByNameUseCase mGetBeersByNameUserCase;

    public BeerViewModel() {
        App.getViewModelComponent().inject(this);
    }

    public void getBeers() {
        mGetAllBeersUserCase.executeUseCase(new BeersDataSource.FetchBeersCallback() {
            @Override
            public void onBeersFetched(List<Beer> beers) {
                error.setValue(false);
                mBeers.setValue(beers);
            }

            @Override
            public void onBeersNotAvailable() {
            }

            @Override
            public void onBeersFetchError() {
                error.setValue(true);
            }
        });
    }

    public void refreshBeers() {
        getBeers();
    }

    public void getBeerByName(String query) {
        // Normalize query String
        query = query.replaceAll(" ", "_").toLowerCase();

        mGetBeersByNameUserCase.executeUseCase(query, new BeersDataSource.SearchBeerCallback() {
            @Override
            public void onSearchBeerSuccess(List<Beer> beers) {
                error.setValue(false);
                mBeers.setValue(beers);
            }

            @Override
            public void onSearchBeerFailure() {
                error.setValue(true);
            }
        });
    }
}
