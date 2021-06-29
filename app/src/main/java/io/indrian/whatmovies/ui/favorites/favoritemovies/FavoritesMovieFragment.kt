package io.indrian.whatmovies.ui.favorites.favoritemovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import io.indrian.whatmovies.adapter.MovieAdapter
import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.databinding.FragmentFavoritesMovieBinding
import io.indrian.whatmovies.ui.detail.DetailActivity
import io.indrian.whatmovies.ui.favorites.FavoritesViewModel
import io.indrian.whatmovies.utils.Event
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class FavoritesMovieFragment : Fragment(), MovieAdapter.OnItemCallbackListener {

    private var _binding: FragmentFavoritesMovieBinding? = null
    private val binding: FragmentFavoritesMovieBinding get() = _binding!!

    private val viewModel: FavoritesViewModel by viewModel()

    private val movieAdapter = MovieAdapter(this)

    private val favoriteMoviesStateObserver = Observer<PagedList<Movie>> { data ->
        Timber.d("favoriteMoviesStateObserver: $data")
        movieAdapter.submitList(data)
        movieAdapter.notifyDataSetChanged()
    }

    private val eventOpenDetailMovieObserver = Observer<Event<Long>> { event ->
        activity?.let {
            event.getContentIfNotHandled()?.let { id ->
                DetailActivity.push(it, id, true)
            }
        }
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
        viewModel.eventOpenDetailMovie.observe(viewLifecycleOwner, eventOpenDetailMovieObserver)
        binding.rvMovies.adapter = movieAdapter
    }

    override fun onClickItem(movie: Movie) {
        viewModel.openDetailMovie(movie.id)
    }

    override fun onWhiteList(movie: Movie) {
        val newMovie = movie.apply {
            isFavorite = !isFavorite
        }
        viewModel.setFavoriteMovie(newMovie)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.getFavoriteMovies().removeObserver(favoriteMoviesStateObserver)
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoritesMovieFragment()
    }
}