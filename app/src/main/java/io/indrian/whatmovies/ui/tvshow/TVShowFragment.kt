package io.indrian.whatmovies.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import io.indrian.whatmovies.adapter.TVShowAdapter
import io.indrian.whatmovies.data.models.TVShow
import io.indrian.whatmovies.databinding.FragmentTVShowBinding
import io.indrian.whatmovies.ui.detail.DetailActivity
import io.indrian.whatmovies.utils.Event
import io.indrian.whatmovies.utils.toGone
import io.indrian.whatmovies.utils.toVisible
import io.indrian.whatmovies.vo.Resource
import io.indrian.whatmovies.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class TVShowFragment : Fragment(), TVShowAdapter.OnItemCallbackListener {

    private var _binding: FragmentTVShowBinding? = null
    private val binding: FragmentTVShowBinding get() = _binding!!

    private val adapter = TVShowAdapter(this)

    private val viewModel: TVShowViewModel by viewModel()

    private val stateTVShowsObserver = Observer<Resource<PagedList<TVShow>>> { state ->
        when (state.status) {
            Status.LOADING -> {
                binding.swipeTvShow.isRefreshing = true
                binding.rvTvShow.toGone()
                binding.errorEmptyLayout.root.toGone()
            }
            Status.SUCCESS -> {
                binding.swipeTvShow.isRefreshing = false
                binding.rvTvShow.toVisible()
                binding.errorEmptyLayout.root.toGone()

                adapter.submitList(state.data)
                adapter.notifyDataSetChanged()
            }
            Status.ERROR -> {
                binding.swipeTvShow.isRefreshing = false
                binding.rvTvShow.toGone()
                binding.errorEmptyLayout.root.toVisible()

                binding.errorEmptyLayout.tvMessage.text = state.message
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
        viewModel.getTVShows().observe(viewLifecycleOwner, stateTVShowsObserver)
        viewModel.eventOpenDetailTVShow.observe(viewLifecycleOwner, eventOpenDetailTVShowObserver)

        binding.rvTvShow.adapter = adapter
        binding.swipeTvShow.setOnRefreshListener {
            viewModel.getTVShows().observe(viewLifecycleOwner, stateTVShowsObserver)
        }
    }

    override fun onClickItem(tvShows: TVShow) {
        viewModel.openDetailTVShow(tvShows.id)
    }

    override fun onWhiteList(tvShows: TVShow) {
        val newTVShow = tvShows.apply {
            isFavorite = !isFavorite
        }
        viewModel.setFavorite(newTVShow)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.getTVShows().removeObserver(stateTVShowsObserver)
        viewModel.eventOpenDetailTVShow.removeObserver(eventOpenDetailTVShowObserver)
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = TVShowFragment()
    }
}