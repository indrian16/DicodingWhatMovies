package io.indrian.whatmovies.ui.movie

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import io.indrian.whatmovies.R
import io.indrian.whatmovies.adapter.MovieAdapter
import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.databinding.FragmentMovieBinding
import io.indrian.whatmovies.ui.detail.DetailActivity
import io.indrian.whatmovies.utils.Event
import io.indrian.whatmovies.utils.toGone
import io.indrian.whatmovies.utils.toVisible
import io.indrian.whatmovies.utils.toast
import io.indrian.whatmovies.vo.Resource
import io.indrian.whatmovies.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MovieFragment : Fragment(), MovieAdapter.OnItemCallbackListener {

    private var _binding: FragmentMovieBinding? = null
    private val binding: FragmentMovieBinding get() = _binding!!

    private val adapter = MovieAdapter(this)

    private val viewModel: MovieViewModel by viewModel()

    @SuppressLint("SetTextI18n")
    private val stateMovieObserver = Observer<Resource<PagedList<Movie>>> { state ->
        when (state.status) {
            Status.LOADING -> {
                Timber.d("Status.LOADING")
                binding.swipeMovie.isRefreshing = true
                binding.rvMovies.toGone()
                binding.errorEmptyLayout.root.toGone()
            }
            Status.SUCCESS -> {
                Timber.d("Status.SUCCESS: ${state.data}")
                binding.swipeMovie.isRefreshing = false
                binding.rvMovies.toVisible()
                binding.errorEmptyLayout.root.toGone()

                adapter.submitList(state.data)
                adapter.notifyDataSetChanged()
            }
            Status.ERROR -> {
                Timber.d("Status.ERROR")
                binding.swipeMovie.isRefreshing = false
                binding.rvMovies.toGone()
                binding.errorEmptyLayout.root.toVisible()

                binding.errorEmptyLayout.tvMessage.text = state.message
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
        viewModel.getMovies().observe(viewLifecycleOwner, stateMovieObserver)
        viewModel.eventOpenDetailMovie.observe(viewLifecycleOwner, eventOpenDetailMovieObserver)

        binding.rvMovies.adapter = adapter
        binding.swipeMovie.setOnRefreshListener {
            viewModel.getMovies().observe(viewLifecycleOwner, stateMovieObserver)
        }
    }

    override fun onClickItem(movie: Movie) {
        viewModel.openDetailMovie(movie.id)
    }

    override fun onWhiteList(movie: Movie) {
        toast(getString(R.string.coming_soon))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.getMovies().removeObserver(stateMovieObserver)
        viewModel.eventOpenDetailMovie.removeObserver(eventOpenDetailMovieObserver)
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieFragment()
    }
}