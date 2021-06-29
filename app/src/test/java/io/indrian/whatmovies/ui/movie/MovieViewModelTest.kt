package io.indrian.whatmovies.ui.movie

import io.indrian.whatmovies.data.repositories.Repository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before


class MovieViewModelTest {

    @RelaxedMockK
    lateinit var repository: Repository

    private lateinit var viewModel: MovieViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = MovieViewModel(repository)
    }
}