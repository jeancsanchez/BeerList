package br.com.jeancarlos.beerlist.beerslist.presentation.ui;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import br.com.jeancarlos.beerlist.App;
import br.com.jeancarlos.beerlist.base.BaseActivity;
import br.com.jeancarlos.beerlist.R;
import br.com.jeancarlos.beerlist.beerslist.domain.model.Beer;
import br.com.jeancarlos.beerlist.beerslist.presentation.BeersListContract;
import br.com.jeancarlos.beerlist.beerslist.presentation.presenters.BeersPresenter;
import br.com.jeancarlos.beerlist.beerslist.presentation.presenters.BeerPresenterModule;
import br.com.jeancarlos.beerlist.beerslist.presentation.presenters.DaggerBeerComponent;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BeersListContract.View {

    @BindView(R.id.recycler_view_beers)
    RecyclerView mRecyclerViewBeers;

    @Inject
    BeersPresenter mBeersPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DaggerBeerComponent.builder()
                .beerRepositoryComponent(App.getBeerRepositoryComponent())
                .beerPresenterModule(new BeerPresenterModule(this))
                .build()
                .inject(this);

        mBeersPresenter.start();
    }

    @Override
    public void setPresenter(BeersListContract.Presenter presenter) {

    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showBeers(List<Beer> beers) {
        for (Beer beer : beers) {
            Log.d(getClass().getSimpleName(), beer.getName());
        }
    }
}
