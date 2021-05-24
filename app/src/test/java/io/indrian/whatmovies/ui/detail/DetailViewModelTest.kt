package io.indrian.whatmovies.ui.detail

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
        assertNotNull(movie)
        assertEquals("Mortal Kombat", movie.title)
    }

    @Test
    fun getDetailTVShow() {
        val tvShow = viewModel.getDetailTVShow(id = 60735)
        assertNotNull(tvShow)
        assertEquals("The Flash", tvShow.name)
    }

    @Test
    fun getDetailErrorMovies() {
        val exception = assertThrows(NullPointerException::class.java) {
            viewModel.getDetailMovies(id = 12345)
        }

        assertEquals("getDetailMovies(id: 12345) is not found", exception.message)
    }

    @Test
    fun getDetailErrorTVShow() {
        val exception = assertThrows(NullPointerException::class.java) {
            viewModel.getDetailTVShow(id = 54321)
        }

        assertEquals("getDetailTVShow(id: 54321) is not found", exception.message)
    }
}