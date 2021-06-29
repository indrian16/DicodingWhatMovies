package io.indrian.whatmovies.ui.favorites.favoritestvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import io.indrian.whatmovies.R
import io.indrian.whatmovies.adapter.TVShowAdapter
import io.indrian.whatmovies.data.models.TVShow
import io.indrian.whatmovies.databinding.FragmentFavoritesTVShowBinding
import io.indrian.whatmovies.ui.detail.DetailActivity
import io.indrian.whatmovies.ui.favorites.FavoritesViewModel
import io.indrian.whatmovies.utils.Event
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class FavoritesTVShowFragment : Fragment(), TVShowAdapter.OnItemCallbackListener {

    private var _binding: FragmentFavoritesTVShowBinding? = null
    private val binding: FragmentFavoritesTVShowBinding get() = _binding!!

    private val viewModel: FavoritesViewModel by viewModel()

    private val tvShowAdapter = TVShowAdapter(this)

    private val favoriteTVShowsStateObserver = Observer<PagedList<TVShow>> { data ->
        Timber.d("favoriteTVShowsStateObserver: $data")

        tvShowAdapter.submitList(data)
        tvShowAdapter.notifyDataSetChanged()
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
        _binding = FragmentFavoritesTVShowBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFavoritesTVShows().observe(viewLifecycleOwner, favoriteTVShowsStateObserver)
        viewModel.eventOpenDetailTVShow.observe(viewLifecycleOwner, eventOpenDetailTVShowObserver)

        binding.rvTvShow.adapter = tvShowAdapter
    }

    override fun onClickItem(tvShows: TVShow) {
        viewModel.openDetailTVShow(tvShows.id)
    }

    override fun onWhiteList(tvShows: TVShow) {
        val newTVShow = tvShows.apply {
            isFavorite = !isFavorite
        }
        viewModel.setFavoriteTVShow(newTVShow)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoritesTVShowFragment()
    }
}