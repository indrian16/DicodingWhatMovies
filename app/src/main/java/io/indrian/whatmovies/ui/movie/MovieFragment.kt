package io.indrian.whatmovies.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import io.indrian.whatmovies.adapter.ItemAdapter
import io.indrian.whatmovies.databinding.FragmentMovieBinding
import io.indrian.whatmovies.ui.detail.DetailActivity

class MovieFragment : Fragment(), ItemAdapter.OnItemCallbackListener {

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

        val adapter = ItemAdapter(this)
        binding.rvMovies.adapter = adapter
    }

    override fun onClickItem() {
        DetailActivity.push(activity as AppCompatActivity)
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