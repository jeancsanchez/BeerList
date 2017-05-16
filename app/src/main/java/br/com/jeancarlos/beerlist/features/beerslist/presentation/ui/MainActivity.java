package br.com.jeancarlos.beerlist.features.beerslist.presentation.ui;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

import javax.inject.Inject;

import br.com.jeancarlos.beerlist.App;
import br.com.jeancarlos.beerlist.R;
import br.com.jeancarlos.beerlist.base.BaseActivity;
import br.com.jeancarlos.beerlist.features.beersdetail.presentation.BeersDetailActivity;
import br.com.jeancarlos.beerlist.features.beerslist.domain.model.Beer;
import br.com.jeancarlos.beerlist.features.beerslist.domain.usecases.GetAllBeersUseCase;
import br.com.jeancarlos.beerlist.features.beerslist.domain.usecases.GetBeerByNameUseCase;
import br.com.jeancarlos.beerlist.features.beerslist.presentation.BeersListContract;
import br.com.jeancarlos.beerlist.features.beerslist.presentation.adapters.BeerAdapter;
import br.com.jeancarlos.beerlist.features.beerslist.presentation.helpers.BeerHelper;
import br.com.jeancarlos.beerlist.features.beerslist.presentation.presenters.BeersPresenter;
import br.com.jeancarlos.beerlist.features.favorites.presentation.ui.FavoritesActivity;
import br.com.jeancarlos.beerlist.injection.components.DaggerBeerPresenterComponent;
import br.com.jeancarlos.beerlist.injection.modules.BeerPresenterModule;
import br.com.jeancarlos.beerlist.util.NetworkUtil;
import br.com.jeancarlos.beerlist.util.SuggestionProvider;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * This is the main screen (Activity) on the application. This class also implements
 * the {@link BeersListContract}.
 * <p>
 * All visual elements related to the beers listing are here.
 * </p>
 */
public class MainActivity extends BaseActivity implements BeersListContract.View,
        BeersListContract.View.OnBeerItemClickedListener, SearchView.OnQueryTextListener,
        BeersListContract.View.OnFavoritesItemClickedListener {

    @BindView(R.id.recycler_view_beers)
    RecyclerView mRecyclerViewBeers;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    @BindView(R.id.linear_layout_not_found_error)
    LinearLayout mLinearMessageNotFound;

    @Inject
    BeersPresenter mBeersPresenter;

    private BeerAdapter mBeerAdapter;

    private SearchView mSearchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initInjections();
        handleIntent(getIntent());
        createAdapter();
        setupRefresh();
        mBeersPresenter.start();
    }

    /**
     * This method provides all dependencies by Dagger2 injection for this view
     */
    private void initInjections() {
        // Inject the presenter
        DaggerBeerPresenterComponent.builder()
                .beerPresenterModule(new BeerPresenterModule(
                        new GetAllBeersUseCase(App.getBeerRepositoryComponent().provideBeersRepository()),
                        new GetBeerByNameUseCase(App.getBeerRepositoryComponent().provideBeersRepository()),
                        this))
                .build()
                .inject(this);
    }


    /**
     * This method initialize the refresh layout
     */
    private void setupRefresh() {
        mSwipeRefresh.setColorSchemeColors(getColorPrimary(), getColorPrimaryDark());
        mSwipeRefresh.setOnRefreshListener(() -> mBeersPresenter.refreshBeers());
    }

    /**
     * Create an adapter for manipulate beers list on {@link RecyclerView}
     */
    private void createAdapter() {
        mBeerAdapter = new BeerAdapter(MainActivity.this);
        mBeerAdapter.setOnBeerItemClickedListener(MainActivity.this);
        mBeerAdapter.setOnFavoritesItemClickedListener(MainActivity.this);

        mRecyclerViewBeers.setLayoutManager(
                new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        mRecyclerViewBeers.setItemViewCacheSize(20);
        mRecyclerViewBeers.setAdapter(mBeerAdapter);
    }


    @Override
    public void setLoadingIndicator(boolean active) {
        // Hides message data not found error to show progress
        if (active) {
            if (mLinearMessageNotFound.getVisibility() == View.VISIBLE) {
                mLinearMessageNotFound.setVisibility(View.GONE);
            }
        } else {
            showMessageNoDataFoundIfListIsEmpty();
        }

        mSwipeRefresh.setRefreshing(active);
    }

    @Override
    public void showBeers(List<Beer> beers) {
        mBeerAdapter.setupBeers(beers);

        if (beers.size() == 0) {
            showDataNotAvailable();
        }
    }

    @Override
    public void onBeersUpdate(List<Beer> beers) {
        mBeerAdapter.updateList(beers);
        showDataNotAvailable();
    }

    @Override
    public void showBeersSearchResult(List<Beer> beers) {
        mBeerAdapter.updateFilterList(beers);

        if (beers.size() == 0) {
            showDataNotAvailable();
        }
    }

    /**
     * This method just call for {@link #showDataNotAvailable()} method. It is just for more
     * readable code
     */
    private void showMessageNoDataFoundIfListIsEmpty() {
        showDataNotAvailable();
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

    /**
     * Displays a message indicating that there is no data available
     */
    @Override
    public void showDataNotAvailable() {
        if (mBeerAdapter.getItemCount() == 0) {
            mLinearMessageNotFound.setVisibility(View.VISIBLE);

        } else {
            mLinearMessageNotFound.setVisibility(View.GONE);
        }
    }

    @Override
    public void favoritesClicked() {
        startActivity(new Intent(this, FavoritesActivity.class));
    }

    @Override
    public void beerClicked(Beer beer) {
        Intent intent = new Intent(this, BeersDetailActivity.class);
        intent.putExtra(BeerHelper.KEY_BEER, beer);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        mSearchView = (SearchView) menu.findItem(R.id.search_view_beers).getActionView();
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        mSearchView.setQueryHint(getResources().getString(R.string.title_search_view_beer));
        mSearchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mBeersPresenter.getBeerByName(query);
        saveRecentQuery(query);
        showMessageNoDataFoundIfListIsEmpty();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mBeerAdapter.performFilter(newText);
        showMessageNoDataFoundIfListIsEmpty();
        return false;
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }


    /**
     * Handle specifics essential for beers search
     *
     * @param intent
     */
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            saveRecentQuery(query);

        } else if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            mSearchView.setQuery(query, false);
            onQueryTextSubmit(query);
        }
    }

    /**
     * Method that saves the queries typed by user on list of the recent searches
     *
     * @param query The text typed by user
     */
    private void saveRecentQuery(String query) {
        SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                SuggestionProvider.AUTHORITY, SuggestionProvider.MODE);

        // Clear the suggestions list if the list is greater then 5
        if (mSearchView.getSuggestionsAdapter().getCount() > 5) {
            suggestions.clearHistory();
        }

        suggestions.saveRecentQuery(query, null);
    }
}
