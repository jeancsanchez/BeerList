package br.com.jeancarlos.beerlist.beersdetail.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import br.com.jeancarlos.beerlist.R;
import br.com.jeancarlos.beerlist.beerslist.domain.model.Beer;
import br.com.jeancarlos.beerlist.beerslist.presentation.ui.BeerHelper;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BeersDetailActivity extends AppCompatActivity {

    @BindView(R.id.image_view_beer_detail)
    ImageView mImageViewBeerDetail;

    private Beer mBeer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beers_detail);
        ButterKnife.bind(this);

        mBeer = getIntent().getExtras().getParcelable(BeerHelper.KEY_BEER);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        supportFinishAfterTransition();
    }

    @Override
    public void onResume() {
        super.onResume();

        Picasso.with(this)
                .load(mBeer.getImageUrl())
                .into(mImageViewBeerDetail);
    }
}
