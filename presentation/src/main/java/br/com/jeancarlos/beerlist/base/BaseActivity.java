package br.com.jeancarlos.beerlist.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public abstract class BaseActivity extends AppCompatActivity {


    /**
     * This method provides all dependencies by Dagger2 injection for this view
     */
    protected abstract void initInjections();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInjections();
    }

    /**
     * Get accent color
     *
     * @return A single color value in the form 0xAARRGGBB.
     */
    protected int getColorAccent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            return getApplicationContext().getColor(R.color.colorAccent);

        return ContextCompat.getColor(getApplicationContext(), R.color.colorAccent);
    }

    /**
     * Get primary color primary
     *
     * @return A single color value in the form 0xAARRGGBB.
     */
    protected int getColorPrimary() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            return getApplicationContext().getColor(R.color.colorPrimary);

        return ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
    }

    /**
     * Get primary color primary dark
     *
     * @return A single color value in the form 0xAARRGGBB.
     */
    protected int getColorPrimaryDark() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            return getApplicationContext().getColor(R.color.colorPrimaryDark);

        return ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark);
    }
}
