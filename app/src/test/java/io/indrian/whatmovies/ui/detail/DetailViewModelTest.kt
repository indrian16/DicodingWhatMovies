package io.indrian.whatmovies.ui.detail

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
    }

    @Test
    fun getDetailMovies() {
        val movie = viewModel.getDetailMovies(id = 460465)
        movie.shouldNotBeNull()
        movie.title.shouldBe("Mortal Kombat")
    }

    @Test
    fun getDetailTVShow() {
        val tvShow = viewModel.getDetailTVShow(id = 60735)
        tvShow.shouldNotBeNull()
        tvShow.name.shouldBe("The Flash")
    }

    @Test
    fun getDetailErrorMovies() {
        val exception = assertThrows(NullPointerException::class.java) {
            viewModel.getDetailMovies(id = 12345)
        }

        exception.message.shouldBe("getDetailMovies(id: 12345) is not found")
    }

    @Test
    fun getDetailErrorTVShow() {
        val exception = assertThrows(NullPointerException::class.java) {
            viewModel.getDetailTVShow(id = 54321)
        }

        exception.message.shouldBe("getDetailTVShow(id: 54321) is not found")
    }
}