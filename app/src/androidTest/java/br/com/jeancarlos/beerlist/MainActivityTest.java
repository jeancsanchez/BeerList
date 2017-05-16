package br.com.jeancarlos.beerlist;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import br.com.jeancarlos.beerlist.features.beerslist.domain.model.Beer;
import br.com.jeancarlos.beerlist.features.beerslist.presentation.ui.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * This class makes some instrumental tests for {@link MainActivity}
 *
 * @author Jean Carlos
 * @since 5/15/17
 */

@RunWith(JUnit4.class)
public class MainActivityTest {

    private static List<Beer> BEERS;

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void setUp() {
        BEERS = FakeBeerList.getFakeBeers();
    }


    // Clicks on second item of list and show the details of it.
    @Test
    public void clicksOnSecondItemOfListAndShowDetails() {

        // Populate de RecyclerView with fake data
        mMainActivityTestRule.getActivity().runOnUiThread(() ->
                mMainActivityTestRule.getActivity().showBeers(BEERS));

        // Check if 'data not found' message is not showing
        onView(withId(R.id.linear_layout_not_found_error))
                .check(matches(not(isDisplayed())));

        // Click on the second item
        onView(withId(R.id.recycler_view_beers))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        // Check if details screen is showing item details
        onView(withId(R.id.text_beer_title_detail))
                .check(matches(isDisplayed()))
                .check(matches(withText(BEERS.get(1).getName())));
    }

    // Clicks on favorites item and show favorites list
    @Test
    public void clicksOnFavoritesItemAndShowFavorites() {

        // Populate de RecyclerView with fake data
        mMainActivityTestRule.getActivity().runOnUiThread(() ->
                mMainActivityTestRule.getActivity().showBeers(BEERS));

        // Check if 'data not found' message is not showing
        onView(withId(R.id.linear_layout_not_found_error))
                .check(matches(not(isDisplayed())));

        // Click on the first item
        onView(withId(R.id.recycler_view_beers))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // Check if favorite screen is showing
        onView(withId(R.id.text_beer_title_detail))
                .check(matches(isDisplayed()))
                .check(matches(withText(BEERS.get(0).getName())));

    }
}
