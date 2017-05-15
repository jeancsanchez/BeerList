package br.com.jeancarlos.beerlist;

import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import br.com.jeancarlos.beerlist.features.beerslist.domain.model.Beer;
import br.com.jeancarlos.beerlist.features.beerslist.presentation.ui.MainActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

/**
 * @author Jean Carlos
 * @since 5/15/17
 */

@RunWith(JUnit4.class)
public class MainActivityTest {

    private static List<Beer> BEERS;
    @Rule
    public ActivityTestRule<MainActivity> mMainActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class) {
                @Override
                protected void afterActivityLaunched() {
                    super.afterActivityLaunched();

                    // populate the view
                    mMainActivityTestRule.getActivity().showBeers(BEERS);
                }
            };

    @Before
    public void setUp() {
        BEERS = new ArrayList<>();
        BEERS.add(new Beer(1));
        BEERS.add(new Beer(2));
        BEERS.add(new Beer(3));
    }

    @Test
    public void clickOnFirstItemOfList() {
        onData(anything())
                .inAdapterView(allOf(withId(R.id.recycler_view_beers), isCompletelyDisplayed()))
                .atPosition(0)
                .perform(click());

        onView(withId(R.id.text_beer_title_detail)).check(matches(isDisplayed()));
    }
}
