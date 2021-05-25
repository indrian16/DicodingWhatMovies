package io.indrian.whatmovies.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.indrian.whatmovies.adapter.TVShowAdapter
import io.indrian.whatmovies.data.models.TVShow
import io.indrian.whatmovies.databinding.FragmentTVShowBinding
import io.indrian.whatmovies.ui.detail.DetailActivity

class TVShowFragment : Fragment(), TVShowAdapter.OnItemCallbackListener {

    private var _binding: FragmentTVShowBinding? = null
    private val binding: FragmentTVShowBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTVShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[TVShowViewModel::class.java]
        val tvShows = viewModel.getTVShows()

        val adapter = TVShowAdapter(this)
        adapter.add(tvShows)
        binding.rvTvShow.adapter = adapter
    }

    override fun onClickItem(tvShows: TVShow) {
        activity?.let {
            DetailActivity.pushTVShow(it, tvShows)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = TVShowFragment()
    }
}