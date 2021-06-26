package io.indrian.whatmovies.ui.favorite

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import io.indrian.whatmovies.ui.favoritemovies.FavoritesMovieFragment
import io.indrian.whatmovies.ui.favoritestvshow.FavoritesTVShowFragment

class SectionsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FavoritesMovieFragment.newInstance()
            1 -> FavoritesTVShowFragment.newInstance()
            else -> FavoritesMovieFragment.newInstance()
        }
    }
}