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

            }
            is CommonState.Empty -> {}
            is CommonState.Loaded -> {
                adapter.add(state.data)
            }
            is CommonState.Error -> {}
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

        binding.rvMovies.adapter = adapter
    }

    override fun onClickItem(movie: Movie) {
        activity?.let {
            DetailActivity.push(it, movie.id, true)
        }
    }

    override fun onWhiteList(movie: Movie) {
        toast(getString(R.string.coming_soon))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.movieState.removeObserver(stateMovieObserver)
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieFragment()
    }
}