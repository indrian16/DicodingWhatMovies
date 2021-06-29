package io.indrian.whatmovies.ui.detail

import io.indrian.whatmovies.data.repositories.Repository
import io.indrian.whatmovies.utils.CommonState
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test


class DetailViewModelTest {

    @RelaxedMockK
    lateinit var repository: Repository

    private lateinit var viewModel: DetailViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = DetailViewModel(repository)
    }

//    @DelicateCoroutinesApi
//    @ExperimentalCoroutinesApi
//    @Test
//    fun getDetailMovies() = coroutinesTestRule.testDispatcher.runBlockingTest {
//        GlobalScope.launch {
//            viewModel.getDetailMovies(12345)
//        }
//        viewModel.stateDetailMovie.getOrAwaitValue().shouldBe(CommonState.Loading)
//    }
//
//    @DelicateCoroutinesApi
//    @ExperimentalCoroutinesApi
//    @Test
//    fun getDetailTVShow() = coroutinesTestRule.testDispatcher.runBlockingTest {
//        GlobalScope.launch {
//            viewModel.getDetailTVShow(54321)
//        }
//        viewModel.stateDetailTVShow.getOrAwaitValue().shouldBe(CommonState.Loading)
//    }
}