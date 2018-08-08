package br.com.jeancarlos.beerlist.features.beersdetail.presentation.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import br.com.jeancarlos.beerlist.App;
import br.com.jeancarlos.beerlist.R;
import br.com.jeancarlos.beerlist.base.BaseActivity;
import br.com.jeancarlos.beerlist.features.beersdetail.presentation.BeersDetailContract;
import br.com.jeancarlos.beerlist.features.beersdetail.presentation.presenters.BeersDetailPresenter;
import br.com.jeancarlos.beerlist.features.beerslist.domain.model.Beer;
import br.com.jeancarlos.beerlist.features.beerslist.presentation.helpers.BeerHelper;
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

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ButterKnife.bind(this);
        mBeer = getIntent().getExtras().getParcelable(BeerHelper.KEY_BEER);
        mBeersDetailPresenter.start(this);
    }

    @Override
    protected void initInjections() {
        App.getActivityComponent().inject(this);
    }


    @Override
    public void showBeerDetails() {
        Picasso.with(BeersDetailActivity.this)
                .load(mBeer.getImageUrl())
                .placeholder(R.drawable.icon_star)
                .into(mImageViewBeerDetail);

        mTextViewTitle.setText(mBeer.getName());
        mTextViewSubTitle.setText(mBeer.getTagLine());
        mTextViewDescription.setText(mBeer.getDescription());
        mImageButtonFavorite.setOnClickListener(this::onFavoriteIconClick);

        if (mBeer.isFavorite()) {
            showFavoriteIcon();

        } else {
            removeFavoriteIcon();
        }
    }

    @Override
    public void showFavoriteIcon() {
        mImageButtonFavorite.setImageResource(0);
        mImageButtonFavorite.setImageResource(R.drawable.ic_favorite_scale_yellow);
    }

    @Override
    public void removeFavoriteIcon() {
        mImageButtonFavorite.setImageResource(0);
        mImageButtonFavorite.setImageResource(R.drawable.ic_favorite_scale);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
