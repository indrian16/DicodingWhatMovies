package io.indrian.whatmovies.ui.splash

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import io.indrian.whatmovies.R
import org.junit.Rule
import org.junit.Test

class SplashActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(SplashActivity::class.java)

    @Test
    fun splashScreen() {
        onView(withId(R.id.splash_animation)).check(matches(isDisplayed()))
        onView(withId(R.id.splash_animation)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_version)).check(matches(isDisplayed()))
    }
}