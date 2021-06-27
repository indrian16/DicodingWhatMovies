package io.indrian.whatmovies.data.source.remote.services

import io.indrian.whatmovies.utils.enqueueResponse
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeEmpty
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MovieServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var movieService: MovieService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        movieService = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MovieService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getMovies() {
        mockWebServer.enqueueResponse("success_movies.json", 200)

        runBlocking {
            val responses = movieService.getMovies()
            val movies = responses.results
            responses.shouldNotBeNull()
            movies.size.shouldBe(20)
            movies[0].title.shouldBe("Cruella")
            movies[0].posterPath.shouldNotBeEmpty()
            movies[0].genreIds.shouldNotBeNull()
        }
    }

    @Test
    fun getDetailMovie() {
        mockWebServer.enqueueResponse("success_detail_movie.json", 200)

        runBlocking {
            val movie = movieService.getDetailMovie(632357)
            movie.shouldNotBeNull()
            movie.title.shouldBe("The Unholy")
            movie.posterPath.shouldNotBeEmpty()
            movie.genres.shouldNotBeEmpty()
            movie.genres[0].name.shouldBe("Horror")
        }
    }

}