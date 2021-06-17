package io.indrian.whatmovies.ui.detail

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


class DetailViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @RelaxedMockK
    lateinit var repository: Repository

    private lateinit var viewModel: DetailViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = DetailViewModel(repository)
    }

    @DelicateCoroutinesApi
    @ExperimentalCoroutinesApi
    @Test
    fun getDetailMovies() = coroutinesTestRule.testDispatcher.runBlockingTest {
        GlobalScope.launch {
            viewModel.getDetailMovies(12345)
        }
        viewModel.stateDetailMovie.getOrAwaitValue().shouldBe(CommonState.Loading)
    }

    @DelicateCoroutinesApi
    @ExperimentalCoroutinesApi
    @Test
    fun getDetailTVShow() = coroutinesTestRule.testDispatcher.runBlockingTest {
        GlobalScope.launch {
            viewModel.getDetailTVShow(54321)
        }
        viewModel.stateDetailTVShow.getOrAwaitValue().shouldBe(CommonState.Loading)
    }
}