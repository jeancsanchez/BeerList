package br.com.jeancarlos.beerlist.beerslist.presentation.presenters;

import java.util.List;

import javax.inject.Inject;

import br.com.jeancarlos.beerlist.beerslist.domain.model.Beer;
import br.com.jeancarlos.beerlist.beerslist.presentation.BeersListContract;
import br.com.jeancarlos.beerlist.beerslist.presentation.ui.MainActivity;
import br.com.jeancarlos.beerlist.data.BeersDataSource;
import br.com.jeancarlos.beerlist.data.BeersRepository;

/**
 * This class represents a presenter for {@link MainActivity}
 * <p>
 * It responds to user actions and manipulates data as required by UI
 * </p>
 *
 * @author Jean Carlos
 * @since 5/10/17
 */

public class BeersPresenter implements BeersListContract.Presenter {

    private BeersRepository mBeerRepository;
    private BeersListContract.View mView;

    @Inject
    BeersPresenter(BeersRepository beersRepository, BeersListContract.View view) {
        mBeerRepository = beersRepository;
        mView = view;
    }


    @Override
    public void start() {
        mView.setLoadingIndicator(true);

        mBeerRepository.fetchBeers(new BeersDataSource.FetchBeersCallback() {
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

        mBeerRepository.searchBeerByName(query, new BeersDataSource.SearchBeerCallback() {
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

        mBeerRepository.fetchBeers(new BeersDataSource.FetchBeersCallback() {
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
