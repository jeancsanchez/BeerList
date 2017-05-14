package br.com.jeancarlos.beerlist.beerslist.presentation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.jeancarlos.beerlist.R;
import br.com.jeancarlos.beerlist.beerslist.domain.model.Beer;
import br.com.jeancarlos.beerlist.beerslist.presentation.ui.OnBeerItemClickedListener;
import butterknife.BindView;
import butterknife.ButterKnife;

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


    public BeerAdapter(Context context) {
        this.mContext = context;
    }

    public void setupBeers(List<Beer> beerList) {
        this.mBeersList = beerList;
        this.mOriginalListFilter.addAll(beerList);
        notifyDataSetChanged();
    }


    @Override
    public BeerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_beer, parent, false);
        return new BeerViewHolder(view);
    }

    public void setOnBeerItemClickedListener(OnBeerItemClickedListener onBeerItemClickedListener) {
        this.mOnBeerClickListener = onBeerItemClickedListener;
    }

    @Override
    public void onBindViewHolder(BeerViewHolder holder, int position) {
        final Beer beerItem = mBeersList.get(position);

        // Load beer's cover image
        Picasso.with(mContext)
                .load(beerItem.getImageUrl())
                .placeholder(R.drawable.icon_star)
                .into(holder.mImageViewBeerCover);

        holder.mTextViewBeerTitle.setText(beerItem.getName());
        holder.mTextViewBeerSubTitle.setText(beerItem.getTagLine());

        holder.containerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnBeerClickListener != null) mOnBeerClickListener.beerClicked(beerItem);
            }
        });
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
