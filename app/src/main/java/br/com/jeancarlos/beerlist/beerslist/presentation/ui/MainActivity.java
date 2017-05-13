package br.com.jeancarlos.beerlist.beerslist.presentation.ui;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import android.view.Menu;

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

public class MainActivity extends BaseActivity implements BeersListContract.View, OnBeerItemClickedListener, SearchView.OnQueryTextListener {

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


    // Inflates the search options menu on toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_view_beers).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setQueryHint(getResources().getString(R.string.title_search_view_beer));
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
