package io.indrian.whatmovies.data.repositories

import io.indrian.whatmovies.data.DummyData
import io.indrian.whatmovies.data.source.RemoteDataSource
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeEmpty
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class RepositoryTest {

    // RemoteDataSource not the Class to be tested
    @RelaxedMockK
    lateinit var remoteDataSource: RemoteDataSource

    // Class under test
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
        repository = Repository(remoteDataSource)
    }

    @After
    fun tearDown() = unmockkAll()

    @ExperimentalCoroutinesApi
    @Test
    fun getMovies() = runBlockingTest {
        coEvery { remoteDataSource.getMovies() } returns dummyMovies
        val movies = repository.getMovies()
        coVerify { remoteDataSource.getMovies() }

        movies.size.shouldNotBeNull()
        movies[0].title.shouldBe(dummyMovie.title)
        movies[0].posterPath.shouldNotBeEmpty()
        movies[0].genreIds.shouldNotBeNull()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getTVShows() = runBlockingTest {
        coEvery { remoteDataSource.getTVShows() } returns dummyTVShows
        val tvShows = repository.getTVShows()
        coVerify { remoteDataSource.getTVShows() }

        tvShows.size.shouldNotBeNull()
        tvShows[0].name.shouldBe(dummyTVShow.name)
        tvShows[0].posterPath.shouldNotBeEmpty()
        tvShows[0].genreIds.shouldNotBeNull()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getDetailMovies() = runBlockingTest {
        coEvery { remoteDataSource.getDetailMovie(movieId) } returns dummyMovie
        val movie = repository.getDetailMovies(movieId)
        coVerify { remoteDataSource.getDetailMovie(movieId) }

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
        coEvery { remoteDataSource.getDetailTVShow(tvShowId) } returns dummyTVShow
        val tvShow = repository.getDetailTVShow(tvShowId)
        coVerify { remoteDataSource.getDetailTVShow(tvShowId) }

        tvShow.shouldNotBeNull()
        tvShow.name.shouldBe(dummyTVShow.name)
        tvShow.backdropPath.shouldNotBeEmpty()
        tvShow.posterPath.shouldNotBeEmpty()
        tvShow.firstAirDate.shouldBe(dummyTVShow.firstAirDate)
        tvShow.overview.shouldBe(dummyTVShow.overview)
        tvShow.genreIds.shouldNotBeNull()
    }
}