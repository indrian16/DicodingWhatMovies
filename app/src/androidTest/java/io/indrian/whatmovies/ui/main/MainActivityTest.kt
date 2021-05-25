package io.indrian.whatmovies.ui.main

import android.support.test.uiautomator.UiDevice
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import io.indrian.whatmovies.R
import io.indrian.whatmovies.data.repositories.Repository
import org.junit.Rule
import org.junit.Test


class MainActivityTest {

    private val movies = Repository.getMovies()
    private val tvShows = Repository.getTVShows()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainAction() {
        onView(withId(R.id.btn_start)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_start)).perform(click())

        onView(withId(R.id.btn_end)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_end)).perform(click())
    }

    @Test
    fun getMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(movies.size))
    }

    @Test
    fun getTVShows() {
        onView(withId(R.id.view_pager)).perform(swipeLeft())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(tvShows.size))
    }

    @Test
    fun getDetailMovie() {
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_title_value)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_value)).check(matches(withText("Mortal Kombat")))
        onView(withId(R.id.tv_overview_value)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview_value)).check(matches(withText("Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.")))

        onView(withId(R.id.btn_end)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_end)).perform(click())
        val mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        mDevice.pressBack()

        onView(withId(R.id.btn_start)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_start)).perform(click())
    }

    @Test
    fun getDetailTVShow() {
        onView(withId(R.id.view_pager)).perform(swipeLeft())
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_title_value)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_value)).check(matches(withText("The Flash")))
        onView(withId(R.id.tv_overview_value)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview_value)).check(matches(withText("After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.")))

        onView(withId(R.id.btn_end)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_end)).perform(click())
        val mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        mDevice.pressBack()

        onView(withId(R.id.btn_start)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_start)).perform(click())
    }
}