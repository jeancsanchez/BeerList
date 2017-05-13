package br.com.jeancarlos.beerlist.beerslist.presentation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import br.com.jeancarlos.beerlist.App;
import br.com.jeancarlos.beerlist.R;
import br.com.jeancarlos.beerlist.base.BaseActivity;
import br.com.jeancarlos.beerlist.beersdetail.presentation.BeersDetailActivity;
import br.com.jeancarlos.beerlist.beerslist.domain.model.Beer;
import br.com.jeancarlos.beerlist.beerslist.presentation.BeersListContract;
import br.com.jeancarlos.beerlist.beerslist.presentation.presenters.BeerPresenterModule;
import br.com.jeancarlos.beerlist.beerslist.presentation.presenters.BeersPresenter;
import br.com.jeancarlos.beerlist.beerslist.presentation.presenters.DaggerBeerComponent;
import br.com.jeancarlos.beerlist.util.NetworkUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BeersListContract.View, OnBeerItemClickedListener {

    @BindView(R.id.recycler_view_beers)
    RecyclerView mRecyclerViewBeers;

    @Inject
    BeersPresenter mBeersPresenter;

    private BeerAdapter mBeerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Inject the presenter
        DaggerBeerComponent.builder()
                .beerRepositoryComponent(App.getBeerRepositoryComponent())
                .beerPresenterModule(new BeerPresenterModule(this))
                .build()
                .inject(this);

        createAdapter();
        mBeersPresenter.start();
    }

    /**
     * Create an adapter for manipulate beers list on {@link RecyclerView}
     */
    private void createAdapter() {
        mBeerAdapter = new BeerAdapter(MainActivity.this);
        mBeerAdapter.setOnBeerItemClickedListener(MainActivity.this);

        mRecyclerViewBeers.setLayoutManager(
                new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        mRecyclerViewBeers.setAdapter(mBeerAdapter);
    }


    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showBeers(List<Beer> beers) {
        mBeerAdapter.setupBeers(beers);
    }


    /**
     * Shows a {@link Snackbar} alerting the user that the data maybe is out of the date, because there is a
     * error of connection with the server
     */
    @Override
    public void showConnectionFailedError() {
        if (!NetworkUtil.hasNetworkConnection(this)) {
            Snackbar.make(
                    mRecyclerViewBeers,
                    getResources().getString(R.string.message_connection_error),
                    Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public void beerClicked(Beer beer) {
        Intent intent = new Intent(this, BeersDetailActivity.class);
        intent.putExtra(BeerHelper.KEY_BEER, beer);
        startActivity(intent);
    }


    @Override
    public void onResume() {
        super.onResume();
    }
}
