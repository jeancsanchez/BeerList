package br.com.jeancarlos.beerlist.beers.presentation.ui;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import br.com.jeancarlos.beerlist.BaseActivity;
import br.com.jeancarlos.beerlist.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R.id.recycler_view_beers)
    RecyclerView mRecyclerViewBeers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
