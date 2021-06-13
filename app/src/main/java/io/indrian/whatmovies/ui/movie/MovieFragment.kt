package io.indrian.whatmovies.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import io.indrian.whatmovies.R
import io.indrian.whatmovies.adapter.MovieAdapter
import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.databinding.FragmentMovieBinding
import io.indrian.whatmovies.ui.detail.DetailActivity
import io.indrian.whatmovies.utils.CommonState
import io.indrian.whatmovies.utils.Event
import io.indrian.whatmovies.utils.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment(), MovieAdapter.OnItemCallbackListener {

    private var _binding: FragmentMovieBinding? = null
    private val binding: FragmentMovieBinding get() = _binding!!

    private val adapter = MovieAdapter(this)

    private val viewModel: MovieViewModel by viewModel()

    private val stateMovieObserver = Observer<CommonState<List<Movie>>> { state ->
        when (state) {
            is CommonState.Loading -> {
                binding.swipeMovie.isRefreshing = true
            }
            is CommonState.Empty -> {
                binding.swipeMovie.isRefreshing = false
            }
            is CommonState.Loaded -> {
                binding.swipeMovie.isRefreshing = false
                adapter.add(state.data)
            }
            is CommonState.Error -> {
                binding.swipeMovie.isRefreshing = false
            }
        }
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
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovies()
        viewModel.movieState.observe(viewLifecycleOwner, stateMovieObserver)
        viewModel.eventOpenDetailMovie.observe(viewLifecycleOwner, eventOpenDetailMovieObserver)

        binding.rvMovies.adapter = adapter
        binding.swipeMovie.setOnRefreshListener { viewModel.getMovies() }
    }

    override fun onClickItem(movie: Movie) {
        viewModel.openDetailMovie(movie.id)
    }

    override fun onWhiteList(movie: Movie) {
        toast(getString(R.string.coming_soon))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.movieState.removeObserver(stateMovieObserver)
        viewModel.eventOpenDetailMovie.removeObserver(eventOpenDetailMovieObserver)
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieFragment()
    }
}