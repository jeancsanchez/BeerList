package br.com.jeancarlos.beerlist.features.beersdetail.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import br.com.jeancarlos.beerlist.App;
import br.com.jeancarlos.beerlist.R;
import br.com.jeancarlos.beerlist.base.BaseActivity;
import br.com.jeancarlos.beerlist.features.beersdetail.domain.DisfavorUseCase;
import br.com.jeancarlos.beerlist.features.beersdetail.domain.FavoriteUseCase;
import br.com.jeancarlos.beerlist.features.beersdetail.presentation.presenters.BeersDetailPresenter;
import br.com.jeancarlos.beerlist.features.beerslist.domain.model.Beer;
import br.com.jeancarlos.beerlist.features.beerslist.presentation.helpers.BeerHelper;
import br.com.jeancarlos.beerlist.injection.components.DaggerBeersDetailPresenterComponent;
import br.com.jeancarlos.beerlist.injection.modules.BeersDetailPresenterModule;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BeersDetailActivity extends BaseActivity implements BeersDetailContract.View {

    @BindView(R.id.image_cover_detail)
    ImageView mImageViewBeerDetail;

    @BindView(R.id.text_beer_title_detail)
    TextView mTextViewTitle;

    @BindView(R.id.text_beer_subtitle_detail)
    TextView mTextViewSubTitle;

    @BindView(R.id.text_view_description)
    TextView mTextViewDescription;

    @BindView(R.id.image_button_favorite)
    ImageButton mImageButtonFavorite;

    @Inject
    BeersDetailPresenter mBeersDetailPresenter;

    private Beer mBeer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beers_detail);

        ButterKnife.bind(this);
        mBeer = getIntent().getExtras().getParcelable(BeerHelper.KEY_BEER);
        initInjections();
    }

    /**
     * This method provides all dependencies by Dagger2 injection for this view
     */
    private void initInjections() {
        FavoriteUseCase favoriteUseCase =
                new FavoriteUseCase(App.getBeerRepositoryComponent().provideBeersRepository());

        DisfavorUseCase disfavorUseCase =
                new DisfavorUseCase(App.getBeerRepositoryComponent().provideBeersRepository());

        // Inject the presenter
        DaggerBeersDetailPresenterComponent.builder()
                .beersDetailPresenterModule(new BeersDetailPresenterModule(
                        favoriteUseCase, disfavorUseCase, this))
                .build()
                .inject(this);
    }

    /**
     * This method loads the beer details
     */
    private void loadBeerDetails() {
        Picasso.with(this)
                .load(mBeer.getImageUrl())
                .placeholder(R.drawable.icon_star)
                .into(mImageViewBeerDetail);

        mTextViewTitle.setText(mBeer.getName());
        mTextViewSubTitle.setText(mBeer.getTagLine());
        mTextViewDescription.setText(mBeer.getDescription());
        mImageButtonFavorite.setOnClickListener(this::onFavoriteIconClick);
    }

    @Override
    public void showFavoriteIcon() {
        mImageButtonFavorite.setBackgroundResource(0);
        mImageButtonFavorite.setBackgroundResource(R.drawable.ic_favorite_scale_yellow);
    }

    @Override
    public void removeFavoriteIcon() {
        mImageButtonFavorite.setBackgroundResource(0);
        mImageButtonFavorite.setBackgroundResource(R.drawable.ic_favorite_scale);
    }

    public void onFavoriteIconClick(View view) {
        if (!mBeer.isFavorite()) {
            mBeersDetailPresenter.favoriteBeer(mBeer);
            showFavoriteIcon();

        } else {
            mBeersDetailPresenter.disfavorBeer(mBeer);
            removeFavoriteIcon();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadBeerDetails();

        if (mBeer.isFavorite()) {
            showFavoriteIcon();

        } else {
            removeFavoriteIcon();
        }
    }
}
