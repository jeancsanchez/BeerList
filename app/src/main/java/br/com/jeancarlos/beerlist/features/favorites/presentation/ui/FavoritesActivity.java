package br.com.jeancarlos.beerlist.features.favorites.presentation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import br.com.jeancarlos.beerlist.App;
import br.com.jeancarlos.beerlist.R;
import br.com.jeancarlos.beerlist.base.BaseActivity;
import br.com.jeancarlos.beerlist.base.BaseView;
import br.com.jeancarlos.beerlist.features.beersdetail.presentation.BeersDetailActivity;
import br.com.jeancarlos.beerlist.features.beerslist.domain.model.Beer;
import br.com.jeancarlos.beerlist.features.beerslist.presentation.helpers.BeerHelper;
import br.com.jeancarlos.beerlist.features.favorites.domain.ShowFavoritesUseCase;
import br.com.jeancarlos.beerlist.features.favorites.presentation.FavoritesContract;
import br.com.jeancarlos.beerlist.features.favorites.presentation.adapters.FavoriteAdapter;
import br.com.jeancarlos.beerlist.features.favorites.presentation.presenters.FavoritePresenter;
import br.com.jeancarlos.beerlist.injection.components.DaggerFavoritePresenterComponent;
import br.com.jeancarlos.beerlist.injection.modules.FavoritePresenterModule;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritesActivity extends BaseActivity implements FavoritesContract.View,
        BaseView.OnBeerItemClickedListener {

    @BindView(R.id.recycler_view_favorites)
    RecyclerView mRecyclerViewFavorites;


    @Inject
    FavoritePresenter mFavoritePresenter;

    private FavoriteAdapter mFavoriteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getResources().getString(R.string.favorites_title));
        }

        ButterKnife.bind(this);
        initInjections();
        initAdapter();
        mFavoritePresenter.start();
    }

    private void initAdapter() {
        mFavoriteAdapter = new FavoriteAdapter(FavoritesActivity.this);
        mFavoriteAdapter.setOnBeerItemClickedListener(FavoritesActivity.this);
        mRecyclerViewFavorites.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerViewFavorites.setAdapter(mFavoriteAdapter);
    }

    /**
     * This method provides all dependencies by Dagger2 injection for this view
     */
    private void initInjections() {
        // Inject the presenter
        DaggerFavoritePresenterComponent.builder()
                .favoritePresenterModule(new FavoritePresenterModule(
                        new ShowFavoritesUseCase(App.getBeerRepositoryComponent().provideBeersRepository()),
                        this))
                .build()
                .inject(this);
    }

    @Override
    public void showFavorites(List<Beer> beers) {
        mFavoriteAdapter.setupBeers(beers);
    }

    @Override
    public void onFavoritesNotFound() {
        String message = getResources().getString(R.string.favorites_not_found_message);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void beerClicked(Beer beer) {
        Intent intent = new Intent(this, BeersDetailActivity.class);
        intent.putExtra(BeerHelper.KEY_BEER, beer);
        startActivity(intent);
    }
}
