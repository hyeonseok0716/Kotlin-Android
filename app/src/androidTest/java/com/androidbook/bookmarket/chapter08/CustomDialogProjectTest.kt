package com.androidbook.bookmarket.chapter08

import android.graphics.Bitmap
import androidx.test.platform.app.InstrumentationRegistry
import java.io.File
import java.io.FileOutputStream
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.androidbook.bookmarket.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CustomDialogProjectTest {
    @Test
    fun calculatorDialogCalculatesClosesAndReopens() {
        ActivityScenario.launch(CustomDialogProject::class.java).use {
            onView(withId(R.id.button)).perform(click())
            onView(withId(R.id.btnFive)).perform(click())
            onView(withId(R.id.btnZero)).perform(click())
            onView(withId(R.id.btnPlus)).perform(click())
            onView(withId(R.id.btnSix)).perform(click())
            onView(withId(R.id.btnEqual)).perform(click())
            onView(withId(R.id.tvResult)).check(matches(withText("56")))
            onView(withId(R.id.tvIntput)).check(matches(withText("50 + 6 = 56")))
            val instrumentation = InstrumentationRegistry.getInstrumentation()
            val bitmap = instrumentation.uiAutomation.takeScreenshot()
            FileOutputStream(File(instrumentation.targetContext.cacheDir, "chapter08-calculator.png")).use { stream ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            }
            bitmap.recycle()
            onView(withId(R.id.buttonclose)).perform(click())
            onView(withId(R.id.button)).check(matches(isDisplayed())).perform(click())
            onView(withId(R.id.tvResult)).check(matches(withText("0")))
            onView(withId(R.id.tvIntput)).check(matches(withText("")))
            onView(withId(R.id.buttonclose)).perform(click())
        }
    }
}
