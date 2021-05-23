package io.indrian.whatmovies.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import io.indrian.whatmovies.ui.movie.MovieFragment
import io.indrian.whatmovies.ui.tvshow.TVShowFragment

class SectionsPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MovieFragment.newInstance()
            1 -> TVShowFragment.newInstance()
            else -> MovieFragment.newInstance()
        }
    }
}