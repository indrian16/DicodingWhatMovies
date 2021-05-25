package io.indrian.whatmovies.ui.tvshow

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.Before
import org.junit.Test

class TVShowViewModelTest {

    private lateinit var viewModel: TVShowViewModel

    @Before
    fun setUp() {
        viewModel = TVShowViewModel()
    }

    @Test
    fun getTVShows() {
        val tvShows = viewModel.getTVShows()
        tvShows.shouldNotBeNull()
        tvShows.size.shouldBe(15)
    }
}