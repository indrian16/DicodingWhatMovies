package io.indrian.whatmovies.ui.favorites

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import io.indrian.whatmovies.ui.favorites.favoritemovies.FavoritesMovieFragment
import io.indrian.whatmovies.ui.favorites.favoritestvshow.FavoritesTVShowFragment

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