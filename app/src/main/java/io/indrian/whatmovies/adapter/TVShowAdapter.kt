package io.indrian.whatmovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.data.models.TVShow
import io.indrian.whatmovies.databinding.ItemLayoutBinding
import io.indrian.whatmovies.di.GlideApp
import io.indrian.whatmovies.utils.AppUtils

class TVShowAdapter(private val onItemCallbackListener: OnItemCallbackListener) : RecyclerView.Adapter<TVShowAdapter.ViewHolder>() {

    private var tvShows: List<TVShow> = arrayListOf()

    fun add(tvShows: List<TVShow>) {
        this.tvShows = tvShows
        notifyDataSetChanged()
    }

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
        holder.bind(tvShows[position])
    }

    override fun getItemCount(): Int = tvShows.size

    interface OnItemCallbackListener {
        fun onClickItem(tvShows: TVShow)
        fun onWhiteList(tvShows: TVShow)
    }

    inner class ViewHolder(private val itemBinding: ItemLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(tvShows: TVShow) {
            with(itemBinding) {
                GlideApp.with(this.root)
                    .load(AppUtils.getImagePath(tvShows.posterPath))
                    .into(imagePoster)

                tvTitle.text = tvShows.name
                ratingBar.rating = AppUtils.getFiveStar(tvShows.voteAverage)
                tvRating.text = tvShows.voteAverage.toString()
                tvGenre.text = tvShows.genreIds.joinToString { AppUtils.getGenreName(it) }
                btnWhiteList.setOnClickListener {
                    onItemCallbackListener.onWhiteList(tvShows)
                }

                root.setOnClickListener {
                    onItemCallbackListener.onClickItem(tvShows)
                }
            }
        }
    }
}