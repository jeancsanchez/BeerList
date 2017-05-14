package br.com.jeancarlos.beerlist.base;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import br.com.jeancarlos.beerlist.R;

/**
 * This class represents a base {@link AppCompatActivity} and provides common resources and features
 * for any Activity.
 *
 * @author Jean Carlos
 * @since 5/10/17.
 */

public class BaseActivity extends AppCompatActivity {

    protected int getColorAccent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            return getApplicationContext().getColor(R.color.colorAccent);

        return ContextCompat.getColor(getApplicationContext(), R.color.colorAccent);
    }

    protected int getColorPrimary() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            return getApplicationContext().getColor(R.color.colorPrimary);

        return ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
    }

    protected int getColorPrimaryDark() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            return getApplicationContext().getColor(R.color.colorPrimaryDark);

        return ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark);
    }
}
