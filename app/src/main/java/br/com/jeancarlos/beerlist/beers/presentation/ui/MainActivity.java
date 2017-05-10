package br.com.jeancarlos.beerlist.beers.presentation.ui;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import br.com.jeancarlos.beerlist.App;
import br.com.jeancarlos.beerlist.BaseActivity;
import br.com.jeancarlos.beerlist.R;
import br.com.jeancarlos.beerlist.beers.domain.model.Beer;
import br.com.jeancarlos.beerlist.beers.presentation.BeersContract;
import br.com.jeancarlos.beerlist.beers.presentation.presenters.BeerPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BeersContract.View {

    @BindView(R.id.recycler_view_beers)
    RecyclerView mRecyclerViewBeers;

    @Inject
    BeerPresenter mBeersPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        App.getBeerPresenterComponent().inject(this);
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
