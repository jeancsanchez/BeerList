package br.com.jeancarlos.beerlist.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.jeancarlos.beerlist.features.beerslist.domain.model.Beer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class makes tests for Beer class model
 *
 * @author Jean Carlos
 * @since 5/14/17
 */

@RunWith(JUnit4.class)
public class BeerTest {

    private Beer mBeer;

    @Before
    public void setUp() {
        mBeer = new Beer();
    }


    // Test if equals method is false when there is two beer with different ids
    @Test
    public void equalsIsFalseWithDifferentIds() {
        Beer newBeer = new Beer();
        newBeer.setId(2);
        mBeer.setId(1);

        assertFalse(mBeer.equals(newBeer));
    }

    // Test if equals method is true when there is two beer with same ids
    @Test
    public void equalsIsTrueWithSameIds() {
        Beer newBeer = new Beer();
        newBeer.setId(2);
        mBeer.setId(2);

        assertTrue(mBeer.equals(newBeer));
    }

    // Test if hashCode method is false when there is two beer with different ids
    @Test
    public void hashCodeIsFalseWithDifferentIds() {
        Beer newBeer = new Beer();
        newBeer.setId(2);
        mBeer.setId(1);

        assertNotEquals(mBeer.hashCode(), newBeer.hashCode());
    }

    // Test if hasCode method is true when there is two beer with same ids
    @Test
    public void hasCodeIsTrueWithSameIds() {
        Beer newBeer = new Beer();
        newBeer.setId(2);
        mBeer.setId(2);

        assertEquals(mBeer.hashCode(), newBeer.hashCode());
    }
}
