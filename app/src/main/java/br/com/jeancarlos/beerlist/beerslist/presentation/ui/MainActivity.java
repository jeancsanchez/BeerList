package br.com.jeancarlos.beerlist.beerslist.presentation.ui;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import br.com.jeancarlos.beerlist.App;
import br.com.jeancarlos.beerlist.base.BaseActivity;
import br.com.jeancarlos.beerlist.R;
import br.com.jeancarlos.beerlist.beerslist.domain.model.Beer;
import br.com.jeancarlos.beerlist.beerslist.presentation.BeersContract;
import br.com.jeancarlos.beerlist.beerslist.presentation.presenters.BeersPresenter;
import br.com.jeancarlos.beerlist.beerslist.presentation.presenters.BeerPresenterModule;
import br.com.jeancarlos.beerlist.beerslist.presentation.presenters.DaggerBeerComponent;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BeersContract.View {

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
                .build();

        mBeersPresenter.start();
    }

    @Override
    public void setPresenter(BeersContract.Presenter presenter) {

    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showBeers(List<Beer> beers) {

    }
}
