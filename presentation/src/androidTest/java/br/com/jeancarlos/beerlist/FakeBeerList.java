package br.com.jeancarlos.beerlist;

import java.util.ArrayList;
import java.util.List;

import com.example.domain.models.Beer;

/**
 * This class just provides a fake Beer List for tests
 *
 * @author Jean Carlos
 * @since 5/16/17
 */

public class FakeBeerList {

    /**
     * Creates a fake beer list
     */
    public static List<Beer> getFakeBeers() {
        List<Beer> BEERS = new ArrayList<>();
        BEERS.add(new Beer(1, "bee1"));
        BEERS.add(new Beer(2, "bee2"));
        BEERS.add(new Beer(3, "beer3"));
        return BEERS;
    }
}
