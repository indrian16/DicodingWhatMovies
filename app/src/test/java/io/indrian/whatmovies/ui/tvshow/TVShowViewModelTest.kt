package io.indrian.whatmovies.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.indrian.whatmovies.data.repositories.Repository
import io.indrian.whatmovies.utils.CoroutinesTestRule
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

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
//
//    @DelicateCoroutinesApi
//    @ExperimentalCoroutinesApi
//    @Test
//    fun getTVShows() = coroutinesTestRule.testDispatcher.runBlockingTest {
//        GlobalScope.launch {
//            viewModel.getTVShows()
//        }
//        viewModel.tvShowState.getOrAwaitValue().shouldBe(CommonState.Loading)
//    }
}