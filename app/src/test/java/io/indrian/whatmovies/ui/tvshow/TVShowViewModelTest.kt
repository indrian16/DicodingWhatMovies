package io.indrian.whatmovies.ui.tvshow

import io.indrian.whatmovies.data.repositories.Repository
import io.indrian.whatmovies.utils.DummyData
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TVShowViewModelTest {

    @Mock
    private lateinit var repository: Repository

    @Test
    fun getTVShows() {
        runBlocking {
            `when`(repository.getTVShows()).thenReturn(DummyData.getTVShows())
            val tvShows = repository.getTVShows()
            verify(repository).getTVShows()

            tvShows.shouldNotBeNull()
            tvShows.size.shouldBe(15)
        }
    }
}