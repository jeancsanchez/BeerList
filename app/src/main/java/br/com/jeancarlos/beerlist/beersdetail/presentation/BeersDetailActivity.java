package br.com.jeancarlos.beerlist.beersdetail.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.jeancarlos.beerlist.R;
import br.com.jeancarlos.beerlist.beerslist.domain.model.Beer;
import br.com.jeancarlos.beerlist.beerslist.presentation.BeerHelper;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BeersDetailActivity extends AppCompatActivity {

    @BindView(R.id.image_cover_detail)
    ImageView mImageViewBeerDetail;

    @BindView(R.id.text_beer_title_detail)
    TextView mTextViewTitle;

    @BindView(R.id.text_beer_subtitle_detail)
    TextView mTextViewSubTitle;

    @BindView(R.id.text_view_description)
    TextView mTextViewDescription;

    private Beer mBeer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beers_detail);
        ButterKnife.bind(this);

        mBeer = getIntent().getExtras().getParcelable(BeerHelper.KEY_BEER);
    }

    // This method loads the beer details
    private void loadBeerDetails() {
        Picasso.with(this)
                .load(mBeer.getImageUrl())
                .placeholder(R.drawable.icon_star)
                .into(mImageViewBeerDetail);

        mTextViewTitle.setText(mBeer.getName());
        mTextViewSubTitle.setText(mBeer.getTagLine());
        mTextViewDescription.setText(mBeer.getDescription());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        supportFinishAfterTransition();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadBeerDetails();
    }
}
