package io.indrian.whatmovies.ui.favoritestvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.indrian.whatmovies.R

class FavoritesTVShowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites_t_v_show, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoritesTVShowFragment()
    }
}