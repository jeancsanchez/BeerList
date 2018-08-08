package br.com.jeancarlos.beerlist.features.favorites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.domain.models.Beer;

import java.util.List;

import javax.inject.Inject;

import br.com.jeancarlos.beerlist.App;
import br.com.jeancarlos.beerlist.R;
import br.com.jeancarlos.beerlist.base.BaseActivity;
import br.com.jeancarlos.beerlist.base.BaseView;
import br.com.jeancarlos.beerlist.features.beersdetail.BeersDetailActivity;
import br.com.jeancarlos.beerlist.features.beerslist.helpers.BeerHelper;
import br.com.jeancarlos.beerlist.features.favorites.adapters.FavoriteAdapter;
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
    }

    private void initAdapter() {
        mFavoriteAdapter = new FavoriteAdapter(FavoritesActivity.this);
        mFavoriteAdapter.setOnBeerItemClickedListener(FavoritesActivity.this);
        mRecyclerViewFavorites.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerViewFavorites.setAdapter(mFavoriteAdapter);
    }

    @Override
    protected void initInjections() {
        App.getActivityComponent().inject(this);
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

    @Override
    public void onResume() {
        super.onResume();
        mFavoritePresenter.start(this);
    }
}
