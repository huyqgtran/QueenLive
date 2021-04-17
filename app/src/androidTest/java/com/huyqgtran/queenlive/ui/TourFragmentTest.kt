package com.huyqgtran.queenlive.ui

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.huyqgtran.queenlive.R
import org.junit.Test
import org.junit.runner.RunWith


@MediumTest
@RunWith(AndroidJUnit4::class)
class TourFragmentTest {

    @Test
    fun activeTourFragment_DisplayedInUi() {
        launchFragmentInContainer<TourFragment>(Bundle(), R.style.AppTheme)
        onView(withId(R.id.tour_rcv)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.tour_rcv))
            .perform(
                // scrollTo will fail the test if no item matches.
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(withText("Crazy Tour"))
                )
            )
    }
}