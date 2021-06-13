package io.indrian.whatmovies.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import io.indrian.whatmovies.R
import io.indrian.whatmovies.adapter.TVShowAdapter
import io.indrian.whatmovies.data.models.TVShow
import io.indrian.whatmovies.databinding.FragmentTVShowBinding
import io.indrian.whatmovies.ui.detail.DetailActivity
import io.indrian.whatmovies.utils.CommonState
import io.indrian.whatmovies.utils.Event
import io.indrian.whatmovies.utils.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class TVShowFragment : Fragment(), TVShowAdapter.OnItemCallbackListener {

    private var _binding: FragmentTVShowBinding? = null
    private val binding: FragmentTVShowBinding get() = _binding!!

    private val adapter = TVShowAdapter(this)

    private val viewModel: TVShowViewModel by viewModel()

    private val stateTVShowsObserver = Observer<CommonState<List<TVShow>>> { state ->
        when (state) {
            is CommonState.Loading -> {
                binding.swipeTvShow.isRefreshing = true
            }
            is CommonState.Empty -> {
                binding.swipeTvShow.isRefreshing = false
            }
            is CommonState.Loaded -> {
                binding.swipeTvShow.isRefreshing = false
                adapter.add(state.data)
            }
            is CommonState.Error -> {
                binding.swipeTvShow.isRefreshing = false
            }
        }
    }

    private val eventOpenDetailTVShowObserver = Observer<Event<Long>> { event ->
        activity?.let {
            event.getContentIfNotHandled()?.let { id ->
                DetailActivity.push(it, id, false)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTVShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTVShows()
        viewModel.tvShowState.observe(viewLifecycleOwner, stateTVShowsObserver)
        viewModel.eventOpenDetailTVShow.observe(viewLifecycleOwner, eventOpenDetailTVShowObserver)

        binding.rvTvShow.adapter = adapter
        binding.swipeTvShow.setOnRefreshListener { viewModel.getTVShows() }
    }

    override fun onClickItem(tvShows: TVShow) {
        viewModel.openDetailTVShow(tvShows.id)
    }

    override fun onWhiteList(tvShows: TVShow) {
        toast(getString(R.string.coming_soon))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.tvShowState.removeObserver(stateTVShowsObserver)
        viewModel.eventOpenDetailTVShow.removeObserver(eventOpenDetailTVShowObserver)
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = TVShowFragment()
    }
}