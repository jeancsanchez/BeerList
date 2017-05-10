package br.com.jeancarlos.beerlist.beers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import br.com.jeancarlos.beerlist.R;
import butterknife.BindView;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recycler_view_beers)
    RecyclerView recyclerViewBeers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
