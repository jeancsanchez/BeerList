package br.com.jeancarlos.beerlist.data;

import br.com.jeancarlos.beerlist.beerslist.domain.model.Beer;

/**
 * This interface represents the contract for any concrete class that pretends
 * manipulate data for {@link Beer} objects.
 *
 * @author jeancarlos
 * @since 5/10/17
 */

public interface BeersDataSource {

    void fetchBeers();
}
