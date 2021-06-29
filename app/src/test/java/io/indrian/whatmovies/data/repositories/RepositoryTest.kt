package io.indrian.whatmovies.data.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import io.indrian.whatmovies.data.DummyData
import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.data.models.TVShow
import io.indrian.whatmovies.data.source.local.LocalDataSource
import io.indrian.whatmovies.data.source.remote.RemoteDataSource
import io.indrian.whatmovies.utils.AppExecutors
import io.indrian.whatmovies.utils.LiveDataTestUtil
import io.indrian.whatmovies.utils.PagedListUtil
import io.indrian.whatmovies.vo.Resource
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeEmpty
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var remoteDataSource: RemoteDataSource

    @RelaxedMockK
    lateinit var localDateSource: LocalDataSource

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
        repository = Repository(remoteDataSource, localDateSource, AppExecutors())
    }

    @After
    fun tearDown() = unmockkAll()

    @Test
    fun getMovies() {
        val dataSourceFactory = mockkClass(DataSource.Factory::class) as DataSource.Factory<Int, Movie>
        every { localDateSource.getMovies() } returns dataSourceFactory

        repository.getMovies()
        val movies = Resource.success(PagedListUtil.mockPagedList(dummyMovies))
        verify { localDateSource.getMovies() }

        movies.data.shouldNotBeNull()
        movies.data?.size.shouldBe(dummyMovies.size)
    }

    @Test
    fun getTVShows() {
        val dataSourceFactory = mockkClass(DataSource.Factory::class) as DataSource.Factory<Int, TVShow>
        every { localDateSource.getTVShows() } returns dataSourceFactory

        repository.getTVShows()
        val tvShows = Resource.success(PagedListUtil.mockPagedList(dummyTVShows))
        verify { localDateSource.getTVShows() }

        tvShows.data.shouldNotBeNull()
        tvShows.data?.size.shouldBe(dummyTVShows.size)
    }

    @Test
    fun getDetailMovies() {
        val liveMovie = MutableLiveData<Movie>()
        liveMovie.value = dummyMovie
        every { localDateSource.getMovie(movieId) } returns liveMovie

        val movie = LiveDataTestUtil.getValue(repository.getDetailMovies(movieId)).data
        verify { localDateSource.getMovie(movieId) }

        movie.shouldNotBeNull()
        movie.title.shouldBe(dummyMovie.title)
        movie.backdropPath.shouldNotBeEmpty()
        movie.posterPath.shouldNotBeEmpty()
        movie.releaseDate.shouldBe(dummyMovie.releaseDate)
        movie.overview.shouldBe(dummyMovie.overview)
        movie.genreIds.shouldNotBeNull()
    }

    @Test
    fun getDetailTVShow() {
        val liveTVShow = MutableLiveData<TVShow>()
        liveTVShow.value = dummyTVShow
        every { localDateSource.getTVShow(tvShowId) } returns liveTVShow

        val tvShow = LiveDataTestUtil.getValue(repository.getDetailTVShow(tvShowId)).data
        verify { localDateSource.getTVShow(tvShowId) }

        tvShow.shouldNotBeNull()
        tvShow.name.shouldBe(dummyTVShow.name)
        tvShow.backdropPath.shouldNotBeEmpty()
        tvShow.posterPath.shouldNotBeEmpty()
        tvShow.firstAirDate.shouldBe(dummyTVShow.firstAirDate)
        tvShow.overview.shouldBe(dummyTVShow.overview)
        tvShow.genreIds.shouldNotBeNull()
    }

    @Test
    fun getFavoriteMovies() {
        val dataSourceFactory = mockkClass(DataSource.Factory::class) as DataSource.Factory<Int, Movie>
        every { localDateSource.getFavoriteMovies() } returns dataSourceFactory
        repository.getFavoriteMovies()

        val movies = Resource.success(PagedListUtil.mockPagedList(dummyMovies))
        verify { localDateSource.getFavoriteMovies() }
        movies.data.shouldNotBeNull()
        movies.data?.size.shouldBe(dummyMovies.size)
    }

    @Test
    fun getFavoriteTVShows() {
        val dataSourceFactory = mockkClass(DataSource.Factory::class) as DataSource.Factory<Int, TVShow>
        every { localDateSource.getFavoriteTVShows() } returns dataSourceFactory
        repository.getFavoriteTVShows()

        val tvShows = Resource.success(PagedListUtil.mockPagedList(dummyTVShows))
        verify { localDateSource.getFavoriteTVShows() }
        tvShows.data.shouldNotBeNull()
        tvShows.data?.size.shouldBe(dummyMovies.size)
    }

    @Test
    fun setFavoriteMovie() {
        val favoriteMovie = dummyMovie.apply {
            isFavorite = true
        }
        repository.updateMovie(favoriteMovie)
        verify { localDateSource.updateMovie(favoriteMovie) }
    }

    @Test
    fun setFavoriteTVShow() {
        val favoriteTVShow = dummyTVShow.apply {
            isFavorite = true
        }
        repository.updateTVShow(favoriteTVShow)
        verify { localDateSource.updateTVShow(favoriteTVShow) }
    }
}