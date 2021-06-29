package io.indrian.whatmovies.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import io.indrian.whatmovies.data.models.TVShow
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

class TVShowViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var repository: Repository

    private lateinit var viewModel: TVShowViewModel

    @RelaxedMockK
    private lateinit var observer: Observer<Resource<PagedList<TVShow>>>

    @RelaxedMockK
    private lateinit var pagedList: PagedList<TVShow>

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = TVShowViewModel(repository)
    }

    @After
    fun tearDown() = unmockkAll()

    @Test
    fun getTVShows() {
        val dummyTVShows = Resource.success(pagedList)
        every { dummyTVShows.data?.size } returns 15

        val liveTVShows = MutableLiveData<Resource<PagedList<TVShow>>>()
        liveTVShows.value = dummyTVShows

        every { repository.getTVShows() } returns liveTVShows
        val movies = viewModel.getTVShows().value?.data
        verify { repository.getTVShows() }

        movies.shouldNotBeNull()
        movies.size.shouldBe(15)

        viewModel.getTVShows().observeForever(observer)
        verify { observer.onChanged(dummyTVShows) }
    }
}