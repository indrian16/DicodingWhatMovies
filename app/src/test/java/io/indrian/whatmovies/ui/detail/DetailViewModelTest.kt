package io.indrian.whatmovies.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.indrian.whatmovies.data.DummyData
import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.data.models.TVShow
import io.indrian.whatmovies.data.repositories.Repository
import io.indrian.whatmovies.vo.Resource
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class DetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DetailViewModel

    private val dummyMovieId = DummyData.getMovies()[0].id
    private val dummyMovie = Resource.success(DummyData.getDetailMovies(dummyMovieId))

    private val dummyTVShowId = DummyData.getTVShows()[0].id
    private val dummyTVShow = Resource.success(DummyData.getDetailTVShow(dummyTVShowId))

    @RelaxedMockK
    private lateinit var repository: Repository

    @RelaxedMockK
    private lateinit var movieObserver: Observer<Resource<Movie>>

    @RelaxedMockK
    private lateinit var tvShowObserver: Observer<Resource<TVShow>>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = DetailViewModel(repository)
    }

    @After
    fun tearDown() = unmockkAll()

    @Test
    fun getDetailMovie() {
        val liveMovie = MutableLiveData<Resource<Movie>>()
        liveMovie.value = dummyMovie
        every { repository.getDetailMovies(dummyMovieId) } returns liveMovie

        viewModel.getDetailMovies(dummyMovieId)
        viewModel.getDetailMovies(dummyMovieId).observeForever(movieObserver)
        verify { movieObserver.onChanged(dummyMovie) }
    }

    @Test
    fun getTVShowDetail() {
        val liveTVShow = MutableLiveData<Resource<TVShow>>()
        liveTVShow.value = dummyTVShow
        every { repository.getDetailTVShow(dummyTVShowId) } returns liveTVShow

        viewModel.getDetailTVShow(dummyTVShowId)
        viewModel.getDetailTVShow(dummyTVShowId).observeForever(tvShowObserver)
        verify { tvShowObserver.onChanged(dummyTVShow) }
    }
}