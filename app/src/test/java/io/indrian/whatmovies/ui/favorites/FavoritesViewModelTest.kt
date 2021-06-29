package io.indrian.whatmovies.ui.favorites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.data.models.TVShow
import io.indrian.whatmovies.data.repositories.Repository
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoritesViewModelTest {

    private lateinit var viewModel: FavoritesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var repository: Repository

    @RelaxedMockK
    private lateinit var movieObserver: Observer<PagedList<Movie>>

    @RelaxedMockK
    private lateinit var tvShowObserver: Observer<PagedList<TVShow>>

    @RelaxedMockK
    private lateinit var pagedListMovie: PagedList<Movie>

    @RelaxedMockK
    private lateinit var pagedListTVShow: PagedList<TVShow>

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = FavoritesViewModel(repository)
    }

    @After
    fun tearDown() = unmockkAll()

    @Test
    fun getFavoriteMovies() {
        val dummyMovies = pagedListMovie
        every { dummyMovies.size } returns 15
        val liveMovies = MutableLiveData<PagedList<Movie>>()
        liveMovies.value = dummyMovies

        every { repository.getFavoriteMovies() } returns liveMovies
        val movies = viewModel.getFavoriteMovies().value
        verify { repository.getFavoriteMovies() }
        movies.shouldNotBeNull()
        movies.size.shouldBe(15)

        viewModel.getFavoriteMovies().observeForever(movieObserver)
        verify { movieObserver.onChanged(dummyMovies) }
    }

    @Test
    fun getFavoriteTVShows() {
        val dummyTVShows = pagedListTVShow
        every { dummyTVShows.size } returns 15
        val liveTVShows = MutableLiveData<PagedList<TVShow>>()
        liveTVShows.value = dummyTVShows

        every { repository.getFavoriteTVShows() } returns liveTVShows
        val tvShows = viewModel.getFavoritesTVShows().value
        verify { repository.getFavoriteTVShows() }
        tvShows.shouldNotBeNull()
        tvShows.size.shouldBe(15)

        viewModel.getFavoritesTVShows().observeForever(tvShowObserver)
        verify { tvShowObserver.onChanged(dummyTVShows) }
    }
}