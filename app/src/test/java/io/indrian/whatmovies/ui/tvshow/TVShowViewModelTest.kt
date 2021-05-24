package io.indrian.whatmovies.ui.tvshow

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class TVShowViewModelTest {

    private lateinit var viewModel: TVShowViewModel

    @Before
    fun setUp() {
        viewModel = TVShowViewModel()
    }

    @Test
    fun getTVShows() {
        val tvShows = viewModel.getTVShows()
        assertNotNull(tvShows)
        assertEquals(15, tvShows.size)
    }
}