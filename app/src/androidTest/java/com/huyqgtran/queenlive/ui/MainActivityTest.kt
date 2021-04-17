package com.huyqgtran.queenlive.ui

import android.view.Gravity
import androidx.appcompat.widget.Toolbar
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.DrawerMatchers
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.huyqgtran.queenlive.R
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @Test
    fun tourFragmentScreen_testDrawer() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        // Check that left drawer is closed at startup
        Espresso.onView(withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(DrawerMatchers.isClosed(Gravity.START))) // Left Drawer should be closed.

        // Open drawer by clicking drawer icon
        var toolbarTitle = ""
        activityScenario
            .onActivity {
                toolbarTitle = it.findViewById<Toolbar>(R.id.toolbar).navigationContentDescription as String
            }
        Espresso.onView(
            withContentDescription(
                toolbarTitle
            )
        ).perform(ViewActions.click())

        // Check if drawer is open
        Espresso.onView(withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(DrawerMatchers.isOpen(Gravity.START))) // Left drawer is open open.
        // When using ActivityScenario.launch, always call close()
        activityScenario.close()
    }
}