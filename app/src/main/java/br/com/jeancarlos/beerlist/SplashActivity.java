package br.com.jeancarlos.beerlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.jeancarlos.beerlist.beerslist.presentation.ui.MainActivity;

/**
 * This class represents the Splash screen
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }
}
