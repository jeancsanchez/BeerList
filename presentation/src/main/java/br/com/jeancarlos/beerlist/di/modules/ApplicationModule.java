package br.com.jeancarlos.beerlist.di.modules;

import android.content.Context;

import com.example.data.BeersRepositoryImpl;

import dagger.Module;
import dagger.Provides;

/**
 * This is a Dagger module. We use this to pass in the Context dependency to the
 * {@link BeersRepositoryImpl}.
 *
 * @author Jean Carlos
 * @since 5/10/17
 */
@Module
public final class ApplicationModule {

    private final Context mContext;

    public ApplicationModule(Context context) {
        mContext = context;
    }

    @Provides
    Context provideContext() {
        return mContext;
    }
}