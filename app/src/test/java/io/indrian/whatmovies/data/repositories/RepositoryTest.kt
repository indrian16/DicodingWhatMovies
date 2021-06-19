package io.indrian.whatmovies.data.repositories

import io.indrian.whatmovies.data.DummyData
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeEmpty
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
    lateinit var remoteRepository: RemoteRepository

    private lateinit var repository: Repository

    private val dummyMovies = DummyData.getMovies()
    private val movieId = dummyMovies[0].id
    private val dummyMovie = DummyData.getDetailMovies(movieId)

    private val dummyTVShows = DummyData.getTVShows()
    private val tvShowId = dummyTVShows[0].id
    private val dummyTVShow = DummyData.getDetailTVShow(tvShowId)

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        repository = Repository(remoteRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getMovies() = runBlockingTest {
        coEvery { remoteRepository.getMovies() } returns dummyMovies
        val movies = repository.getMovies()
        coVerify { remoteRepository.getMovies() }

        movies.size.shouldNotBeNull()
        movies[0].title.shouldBe(dummyMovie.title)
        movies[0].posterPath.shouldNotBeEmpty()
        movies[0].genreIds.shouldNotBeNull()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getTVShows() = runBlockingTest {
        coEvery { remoteRepository.getTVShows() } returns dummyTVShows
        val tvShows = repository.getTVShows()
        coVerify { remoteRepository.getTVShows() }

        tvShows.size.shouldNotBeNull()
        tvShows[0].name.shouldBe(dummyTVShow.name)
        tvShows[0].posterPath.shouldNotBeEmpty()
        tvShows[0].genreIds.shouldNotBeNull()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getDetailMovies() = runBlockingTest {
        coEvery { remoteRepository.getDetailMovie(movieId) } returns dummyMovie
        val movie = repository.getDetailMovies(movieId)
        coVerify { remoteRepository.getDetailMovie(movieId) }

        movie.shouldNotBeNull()
        movie.title.shouldBe(dummyMovie.title)
        movie.backdropPath.shouldNotBeEmpty()
        movie.posterPath.shouldNotBeEmpty()
        movie.releaseDate.shouldBe(dummyMovie.releaseDate)
        movie.overview.shouldBe(dummyMovie.overview)
        movie.genreIds.shouldNotBeNull()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getDetailTVShow() = runBlockingTest {
        coEvery { remoteRepository.getDetailTVShow(tvShowId) } returns dummyTVShow
        val tvShow = repository.getDetailTVShow(tvShowId)
        coVerify { remoteRepository.getDetailTVShow(tvShowId) }

        tvShow.shouldNotBeNull()
        tvShow.name.shouldBe(dummyTVShow.name)
        tvShow.backdropPath.shouldNotBeEmpty()
        tvShow.posterPath.shouldNotBeEmpty()
        tvShow.firstAirDate.shouldBe(dummyTVShow.firstAirDate)
        tvShow.overview.shouldBe(dummyTVShow.overview)
        tvShow.genreIds.shouldNotBeNull()
    }
}