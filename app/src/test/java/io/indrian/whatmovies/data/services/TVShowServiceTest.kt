package io.indrian.whatmovies.data.services

import io.indrian.whatmovies.utils.enqueueResponse
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

class TVShowServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var tvShowService: TVShowService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        tvShowService = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(TVShowService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getTVShows() {
        mockWebServer.enqueueResponse("success_tv_show.json", 200)

        runBlocking {
            val responses = tvShowService.getTVShows()
            val tvShows = responses.results
            responses.shouldNotBeNull()
            tvShows.size.shouldBe(20)
            tvShows[0].name.shouldBe("Loki")
            tvShows[0].posterPath.shouldNotBeEmpty()
            tvShows[0].genreIds.shouldNotBeNull()
        }
    }
}