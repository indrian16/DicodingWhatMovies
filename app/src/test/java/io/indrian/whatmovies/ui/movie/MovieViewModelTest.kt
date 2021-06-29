package io.indrian.whatmovies.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.data.repositories.Repository
import io.indrian.whatmovies.vo.Resource
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


class MovieViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var repository: Repository

    private lateinit var viewModel: MovieViewModel

    @RelaxedMockK
    private lateinit var observer: Observer<Resource<PagedList<Movie>>>

    @RelaxedMockK
    private lateinit var pagedList: PagedList<Movie>

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = MovieViewModel(repository)
    }

    @After
    fun tearDown() = unmockkAll()

    @Test
    fun getMovies() {
        val dummyMovies = Resource.success(pagedList)
        every { dummyMovies.data?.size } returns 15

        val liveMovies = MutableLiveData<Resource<PagedList<Movie>>>()
        liveMovies.value = dummyMovies

        every { repository.getMovies() } returns liveMovies
        val movies = viewModel.getMovies().value?.data
        verify { repository.getMovies() }

        movies.shouldNotBeNull()
        movies.size.shouldBe(15)

        viewModel.getMovies().observeForever(observer)
        verify { observer.onChanged(dummyMovies) }
    }
}