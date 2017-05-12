package br.com.jeancarlos.beerlist.beerslist.presentation.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.jeancarlos.beerlist.R;
import br.com.jeancarlos.beerlist.beerslist.domain.model.Beer;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * This class represents a item Beer on {@link android.support.v7.widget.RecyclerView}
 *
 * @author Jean Carlos
 * @since 5/12/17
 */

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.BeerViewHolder> {

    private final Context context;
    private List<Beer> beerList;

    public BeerAdapter(Context context) {
        this.context = context;
    }

    public void setupBeers(List<Beer> beerList) {
        this.beerList = beerList;
        notifyDataSetChanged();
    }

    @Override
    public BeerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_beer, parent, false);
        return new BeerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BeerViewHolder holder, int position) {
        Beer beerItem = beerList.get(position);

        // Load beer's cover image
        Picasso.with(context)
                .load(beerItem.getImageUrl())
                .placeholder(R.drawable.icon_star)
                .into(holder.mImageViewBeerCover);

        holder.mTextViewBeerTitle.setText(beerItem.getName());
        holder.mTextViewBeerSubTitle.setText(beerItem.getTagLine());
    }

    @Override
    public int getItemCount() {
        return beerList == null ? 0 : beerList.size();
    }

    public class BeerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_cover)
        ImageView mImageViewBeerCover;

        @BindView(R.id.text_beer_title)
        TextView mTextViewBeerTitle;

        @BindView(R.id.text_beer_subtitle)
        TextView mTextViewBeerSubTitle;

        View itemView;

        public BeerViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}
