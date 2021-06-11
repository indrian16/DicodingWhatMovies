package io.indrian.whatmovies.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.indrian.whatmovies.R
import io.indrian.whatmovies.adapter.MovieAdapter
import io.indrian.whatmovies.databinding.FragmentMovieBinding
import io.indrian.whatmovies.ui.detail.DetailActivity
import io.indrian.whatmovies.utils.toast

class MovieFragment : Fragment(), MovieAdapter.OnItemCallbackListener {

    private var _binding: FragmentMovieBinding? = null
    private val binding: FragmentMovieBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MovieViewModel::class.java]
        val movies = viewModel.getMovies()

        val adapter = MovieAdapter(this)
        adapter.add(movies)
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
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieFragment()
    }
}