package br.com.jeancarlos.beerlist.features.favorites.adapters;

import android.content.Context;

import java.util.List;

import br.com.jeancarlos.beerlist.base.BaseView;
import com.example.domain.models.Beer;
import br.com.jeancarlos.beerlist.features.beerslist.adapters.BeerAdapter;

/**
 * This class represents a item Beer on favorite list. This class extends {@link BeerAdapter}
 *
 * @author Jean Carlos
 * @since 5/16/17
 */

public class FavoriteAdapter extends BeerAdapter {

    public FavoriteAdapter(Context context) {
        super(context);
        super.canShowFavoritesItem(false);
    }

    @Override
    public void setOnBeerItemClickedListener(BaseView.OnBeerItemClickedListener onBeerItemClickedListener) {
        super.setOnBeerItemClickedListener(onBeerItemClickedListener);
    }

    @Override
    public void setupBeers(List<Beer> beersList) {
        super.setupBeers(beersList);
    }
}
