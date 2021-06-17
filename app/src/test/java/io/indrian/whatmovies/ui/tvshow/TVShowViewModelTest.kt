package io.indrian.whatmovies.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.indrian.whatmovies.data.repositories.Repository
import io.indrian.whatmovies.utils.CommonState
import io.indrian.whatmovies.utils.CoroutinesTestRule
import io.indrian.whatmovies.utils.getOrAwaitValue
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TVShowViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @RelaxedMockK
    lateinit var repository: Repository

    private lateinit var viewModel: TVShowViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = TVShowViewModel(repository)
    }

    @DelicateCoroutinesApi
    @ExperimentalCoroutinesApi
    @Test
    fun getTVShows() = coroutinesTestRule.testDispatcher.runBlockingTest {
        GlobalScope.launch {
            viewModel.getTVShows()
        }
        viewModel.tvShowState.getOrAwaitValue().shouldBe(CommonState.Loading)
    }
}