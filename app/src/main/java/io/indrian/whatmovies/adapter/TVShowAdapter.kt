package io.indrian.whatmovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.indrian.whatmovies.R
import io.indrian.whatmovies.data.models.TVShow
import io.indrian.whatmovies.databinding.ItemLayoutBinding
import io.indrian.whatmovies.di.GlideApp
import io.indrian.whatmovies.utils.AppUtils

class TVShowAdapter(private val onItemCallbackListener: OnItemCallbackListener) : PagedListAdapter<TVShow, TVShowAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

    interface OnItemCallbackListener {
        fun onClickItem(tvShows: TVShow)
        fun onWhiteList(tvShows: TVShow)
    }

    inner class ViewHolder(private val itemBinding: ItemLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(tvShows: TVShow) {
            with(itemBinding) {
                GlideApp.with(this.root)
                    .load(AppUtils.getImagePath(tvShows.posterPath))
                    .error(R.drawable.ic_launcher_foreground)
                    .into(imagePoster)

                tvTitle.text = tvShows.name
                ratingBar.rating = AppUtils.getFiveStar(tvShows.voteAverage)
                tvRating.text = tvShows.voteAverage.toString()
                tvGenre.text = tvShows.genreIds.joinToString { AppUtils.getGenreName(it) }
                btnWhiteList.setOnClickListener {
                    onItemCallbackListener.onWhiteList(tvShows)
                }

                if (tvShows.isFavorite) {
                    btnWhiteList.icon = ContextCompat.getDrawable(itemBinding.root.context, R.drawable.ic_heart_fill)
                } else {
                    btnWhiteList.icon = ContextCompat.getDrawable(itemBinding.root.context, R.drawable.ic_heart_outlined)
                }

                root.setOnClickListener {
                    onItemCallbackListener.onClickItem(tvShows)
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TVShow>() {
            override fun areItemsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
                return oldItem == newItem
            }
        }
    }
}