package io.indrian.whatmovies.ui.detail

import io.indrian.whatmovies.data.repositories.Repository
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock


class DetailViewModelTest {

    @Mock
    private lateinit var repository: Repository

    @Test
    fun getDetailMovies() {
        runBlocking {
            val movie = repository.getDetailMovies(id = 460465)
//            movie.shouldNotBeNull()
//            movie.title.shouldBe("Mortal Kombat")
        }
    }

    @Test
    fun getDetailTVShow() {
        runBlocking {
            val tvShow = repository.getDetailTVShow(id = 60735)
//            tvShow.shouldNotBeNull()
//            tvShow.name.shouldBe("The Flash")
        }
    }

    @Test
    fun getDetailErrorMovies() {
        val exception = assertThrows(NullPointerException::class.java) {
//            viewModel.getDetailMovies(id = 12345)
        }

        exception.message.shouldBe("getDetailMovies(id: 12345) is not found")
    }

    @Test
    fun getDetailErrorTVShow() {
        val exception = assertThrows(NullPointerException::class.java) {
//            viewModel.getDetailTVShow(id = 54321)
        }

        exception.message.shouldBe("getDetailTVShow(id: 54321) is not found")
    }
}