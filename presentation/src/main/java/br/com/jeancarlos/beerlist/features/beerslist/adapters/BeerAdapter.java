package br.com.jeancarlos.beerlist.features.beerslist.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.domain.models.Beer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.jeancarlos.beerlist.R;
import br.com.jeancarlos.beerlist.base.BaseView.OnBeerItemClickedListener;
import br.com.jeancarlos.beerlist.features.beerslist.FavoriteCallback;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * This class represents a item Beer on {@link android.support.v7.widget.RecyclerView}
 *
 * @author Jean Carlos
 * @since 5/12/17
 */

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.BeerViewHolder> {
    private final Context mContext;
    private List<Beer> mBeersList;
    private List<Beer> mOriginalListFilter = new ArrayList<>();
    private OnBeerItemClickedListener mOnBeerClickListener;
    private FavoriteCallback mOnFavoritesClickListener;
    private boolean mCanShowFavoritesItem;


    public BeerAdapter(Context context) {
        this.mContext = context;
        canShowFavoritesItem(true);
    }

    /**
     * This method starts the beers list
     *
     * @param beersList The beers list
     */
    public void setupBeers(List<Beer> beersList) {
        this.mBeersList = beersList;
        this.mOriginalListFilter.addAll(beersList);
        notifyDataSetChanged();
    }

    /**
     * This method updates the beers list when it receives a new list from the search
     *
     * @param beersList The beers list
     */
    public void updateFilterList(List<Beer> beersList) {
        mBeersList = beersList;
        notifyDataSetChanged();
        updateList(beersList);
    }


    /**
     * This method updates the beers list with a new list
     *
     * @param newList The new beers list to be included
     */
    public void updateList(List<Beer> newList) {
        for (Beer beer : newList) {
            if (!mOriginalListFilter.contains(beer)) {
                mOriginalListFilter.add(beer);
            }
        }
    }

    @Override
    public BeerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_beer, parent, false);
        return new BeerViewHolder(view);
    }

    /**
     * Set the beer item click listener
     *
     * @param onBeerItemClickedListener A callback listener for handle the beer item click
     */
    public void setOnBeerItemClickedListener(OnBeerItemClickedListener onBeerItemClickedListener) {
        this.mOnBeerClickListener = onBeerItemClickedListener;
    }

    /**
     * Set the favorites item click listener
     *
     * @param onFavoritesItemClickedListener A callback listener for handle the favorites item click
     */
    public void setOnFavoritesItemClickedListener(FavoriteCallback onFavoritesItemClickedListener) {
        this.mOnFavoritesClickListener = onFavoritesItemClickedListener;
    }

    @Override
    public void onBindViewHolder(BeerViewHolder holder, int position) {

        // Set favorite beers on top if necessary
        if (mCanShowFavoritesItem && position == 0) {
            Picasso.with(mContext)
                    .load(R.drawable.ic_favorite)
                    .into(holder.mImageViewBeerCover);

            holder.mTextViewBeerTitle.setText(mContext.getResources().getString(
                    R.string.favorites_title));
            holder.mTextViewBeerSubTitle.setText(mContext.getResources().getString(
                    R.string.favorites_sub_title));
            holder.containerView.setOnClickListener(this::onFavoritesClick);

        } else {
            final Beer beerItem = mBeersList.get(position);

            // Load beer's cover image
            Picasso.with(mContext)
                    .load(beerItem.getImageUrl())
                    .placeholder(R.drawable.icon_star)
                    .into(holder.mImageViewBeerCover);

            holder.mTextViewBeerTitle.setText(beerItem.getName());
            holder.mTextViewBeerSubTitle.setText(beerItem.getTagLine());

            holder.containerView.setOnClickListener(v -> {
                checkNotNull(mOnBeerClickListener);
                mOnBeerClickListener.beerClicked(beerItem);
            });
        }
    }

    private void onFavoritesClick(View view) {
        checkNotNull(mOnFavoritesClickListener);
        mOnFavoritesClickListener.favoritesClicked();
    }

    /**
     * This method verify if can show the favorites item on top of the list
     *
     * @param canShow A boolean for set if can show or not
     */
    public void canShowFavoritesItem(boolean canShow) {
        this.mCanShowFavoritesItem = canShow;
    }

    @Override
    public int getItemCount() {
        return mBeersList == null ? 0 : mBeersList.size();
    }

    /**
     * Method for search the list locally with the items already loaded on list
     *
     * @param query String that contains the query
     */
    public void performFilter(String query) {
        if (mBeersList != null) {
            if (query.isEmpty()) {
                mBeersList = mOriginalListFilter;

            } else {
                List<Beer> result = new ArrayList<>();
                for (Beer beer : mOriginalListFilter) {
                    if (beer.getName().toLowerCase().contains(query.toLowerCase())) {
                        result.add(beer);
                    }
                }

                mBeersList = result;
            }

            notifyDataSetChanged();
        }
    }


    public class BeerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_cover)
        ImageView mImageViewBeerCover;

        @BindView(R.id.text_beer_title)
        TextView mTextViewBeerTitle;

        @BindView(R.id.text_beer_subtitle)
        TextView mTextViewBeerSubTitle;

        View containerView;

        public BeerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            this.containerView = itemView;
        }
    }
}
