package br.com.jeancarlos.beerlist.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * This class provides one method that checks if there is network connection
 *
 * @author Jean Carlos
 * @since 5/13/17
 */

public class NetworkUtil {

    public static boolean hasNetworkConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();

        return info != null && info.isConnectedOrConnecting();
    }
}
