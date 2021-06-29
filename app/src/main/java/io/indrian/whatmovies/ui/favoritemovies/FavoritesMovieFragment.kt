package io.indrian.whatmovies.ui.favoritemovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.databinding.FragmentFavoritesMovieBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class FavoritesMovieFragment : Fragment() {

    private var _binding: FragmentFavoritesMovieBinding? = null
    private val binding: FragmentFavoritesMovieBinding get() = _binding!!

    private val viewModel: FavoritesMovieViewModel by viewModel()

    private val favoriteMoviesStateObserver = Observer<PagedList<Movie>> { state ->
        Timber.d("favoriteMoviesStateObserver: $state")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesMovieBinding.inflate(
            LayoutInflater.from(context),
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFavoriteMovies().observe(viewLifecycleOwner, favoriteMoviesStateObserver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoritesMovieFragment()
    }
}