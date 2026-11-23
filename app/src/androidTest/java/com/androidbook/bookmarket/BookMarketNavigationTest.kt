package com.androidbook.bookmarket

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BookMarketNavigationTest {
    @Test
    fun drawerAndActionMenusConnectTheBookScreens() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        ActivityScenario.launch(MainActivity::class.java).use {
            onView(withId(R.id.imageButton)).perform(click())
            onView(withContentDescription(R.string.open_navigation)).perform(click())
            onView(withText(R.string.nav_login)).check(matches(isDisplayed())).perform(click())
            onView(withId(R.id.imageButton1)).perform(click())
            onView(withId(R.id.book3)).perform(click())
            onView(withId(R.id.book_tv01)).check(matches(withText("BOOK1236")))
            onView(withId(R.id.book_tv02)).check(matches(withText(R.string.book_php)))
            onView(withId(R.id.book_tv03)).check(matches(withText("30000")))
            onView(withContentDescription(R.string.menu_cart_title)).perform(click())
            openActionBarOverflowOrOptionsMenu(context)
            onView(withText(R.string.books_title)).perform(click())
            onView(withId(R.id.book1)).check(matches(isDisplayed()))
            openActionBarOverflowOrOptionsMenu(context)
            onView(withText(R.string.menu_home_title)).perform(click())
            onView(withId(R.id.imageButton)).check(matches(isDisplayed()))
        }
    }
}
