package br.com.jeancarlos.beerlist.features.beerslist;

import java.util.List;

import javax.inject.Inject;

import br.com.jeancarlos.beerlist.base.BaseView;
import com.example.data.BeersDataSource;
import br.com.jeancarlos.beerlist.di.scopes.PearActivity;
import com.example.domain.models.Beer;
import com.example.domain.usecases.GetAllBeersUseCase;
import com.example.domain.usecases.GetBeerByNameUseCase;

/**
 * This class represents a presenter for {@link MainActivity} and is a concrete implementation of
 * {@link BeersListContract}
 * <p>
 * It responds to user actions and manipulates data as required by UI
 * </p>
 *
 * @author Jean Carlos
 * @since 5/10/17
 */

@PearActivity
public class BeersPresenter implements BeersListContract.Presenter {

    private GetAllBeersUseCase mGetAllBeersUserCase;
    private GetBeerByNameUseCase mGetBeersByNameUserCase;
    private BeersListContract.View mView;

    @Inject
    BeersPresenter(
            GetAllBeersUseCase getAllBeersUseCase,
            GetBeerByNameUseCase getBeerByNameUseCase
    ) {
        mGetAllBeersUserCase = getAllBeersUseCase;
        mGetBeersByNameUserCase = getBeerByNameUseCase;
    }


    @Override
    public void start(BaseView view) {
        mView = (BeersListContract.View) view;
        mView.setLoadingIndicator(true);

        mGetAllBeersUserCase.executeUseCase(new BeersDataSource.FetchBeersCallback() {
            @Override
            public void onBeersFetched(List<Beer> beers) {
                mView.showBeers(beers);
                mView.setLoadingIndicator(false);
            }

            @Override
            public void onBeersNotAvailable() {
                mView.showConnectionFailedError();
                mView.setLoadingIndicator(false);
            }

            @Override
            public void onBeersFetchError() {
                mView.showConnectionFailedError();
                mView.setLoadingIndicator(false);
            }
        });
    }

    @Override
    public void getBeerByName(String query) {
        mView.setLoadingIndicator(true);

        // Normalize query String
        query = query.replaceAll(" ", "_").toLowerCase();

        mGetBeersByNameUserCase.executeUseCase(query, new BeersDataSource.SearchBeerCallback() {
            @Override
            public void onSearchBeerSuccess(List<Beer> beers) {
                mView.showBeersSearchResult(beers);
                mView.setLoadingIndicator(false);
            }

            @Override
            public void onSearchBeerFailure() {
                mView.showConnectionFailedError();
                mView.setLoadingIndicator(false);
            }
        });
    }

    @Override
    public void refreshBeers() {
        mView.setLoadingIndicator(true);

        mGetAllBeersUserCase.executeUseCase(new BeersDataSource.FetchBeersCallback() {
            @Override
            public void onBeersFetched(List<Beer> beers) {
                mView.onBeersUpdate(beers);
                mView.setLoadingIndicator(false);
            }

            @Override
            public void onBeersNotAvailable() {
                mView.showConnectionFailedError();
                mView.setLoadingIndicator(false);
            }

            @Override
            public void onBeersFetchError() {
                mView.showConnectionFailedError();
                mView.setLoadingIndicator(false);
            }
        });
    }
}
