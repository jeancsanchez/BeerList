package br.com.jeancarlos.beerlist.util;

import android.content.SearchRecentSuggestionsProvider;


/**
 * This class create a simple search suggestions provider.
 * It creates suggestions (as the user types) based on recent queries and/or recent views.
 *
 * @author Jean Carlos
 * @since 5/14/17
 */

public class SuggestionProvider extends SearchRecentSuggestionsProvider {
    public final static String AUTHORITY = "br.com.jeancarlos.beerlist.util.SuggestionProvider";
    public final static int MODE = DATABASE_MODE_QUERIES;

    public SuggestionProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }
}
