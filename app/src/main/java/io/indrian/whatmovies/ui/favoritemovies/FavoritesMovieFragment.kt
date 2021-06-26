package io.indrian.whatmovies.ui.favoritemovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.indrian.whatmovies.R

class FavoritesMovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites_movie, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoritesMovieFragment()
    }
}