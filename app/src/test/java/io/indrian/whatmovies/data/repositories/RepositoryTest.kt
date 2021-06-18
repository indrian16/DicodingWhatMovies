package io.indrian.whatmovies.data.repositories

import io.indrian.whatmovies.utils.DummyData
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class RepositoryTest {

    @RelaxedMockK
    lateinit var repository: Repository

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @ExperimentalCoroutinesApi
    @Test
    fun getMovies() = runBlockingTest {
        coEvery { repository.getMovies() } returns DummyData.getMovies()
        val movies = repository.getMovies()
        coVerify { repository.getMovies() }

        movies.size.shouldNotBeNull()
        movies[0].title.shouldBe("Mortal Kombat")
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getTVShows() = runBlockingTest {
        coEvery { repository.getTVShows() } returns DummyData.getTVShows()
        val tvShows = repository.getTVShows()
        coVerify { repository.getTVShows() }

        tvShows.size.shouldNotBeNull()
        tvShows[0].name.shouldBe("The Flash")
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getDetailMovies() = runBlockingTest {
        val id = 460465L
        coEvery { repository.getDetailMovies(id) } returns DummyData.getDetailMovies(id)
        val movie = repository.getDetailMovies(id)
        coVerify { repository.getDetailMovies(id) }

        movie.shouldNotBeNull()
        movie.title.shouldBe("Mortal Kombat")
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getDetailTVShow() = runBlockingTest {
        val id = 60735L
        coEvery { repository.getDetailTVShow(id) } returns DummyData.getDetailTVShow(id)
        val tvShow = repository.getDetailTVShow(id)
        coVerify { repository.getDetailTVShow(id) }

        tvShow.shouldNotBeNull()
        tvShow.name.shouldBe("The Flash")
    }
}