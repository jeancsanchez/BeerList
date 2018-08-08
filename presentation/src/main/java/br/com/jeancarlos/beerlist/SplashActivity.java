package br.com.jeancarlos.beerlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.jeancarlos.beerlist.features.beerslist.MainActivity;

/**
 * This class represents the Splash screen. We just need to call for {@link MainActivity} when this
 * get ready. This approach prevents the blank screen at application launch.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }
}
