package io.indrian.whatmovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.indrian.whatmovies.R
import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.databinding.ItemLayoutBinding
import io.indrian.whatmovies.di.GlideApp
import io.indrian.whatmovies.utils.AppUtils

class MovieAdapter(private val onItemCallbackListener: OnItemCallbackListener) : PagedListAdapter<Movie, MovieAdapter.ViewHolder>(DIFF_CALLBACK) {

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
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    interface OnItemCallbackListener {
        fun onClickItem(movie: Movie)
        fun onWhiteList(movie: Movie)
    }

    inner class ViewHolder(private val itemBinding: ItemLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(movie: Movie) {
            with(itemBinding) {
                GlideApp.with(this.root)
                    .load(AppUtils.getImagePath(movie.posterPath))
                    .error(R.drawable.ic_launcher_foreground)
                    .into(imagePoster)

                tvTitle.text = movie.title
                ratingBar.rating = AppUtils.getFiveStar(movie.voteAverage)
                tvRating.text = movie.voteAverage.toString()
                tvGenre.text = movie.genreIds.joinToString { AppUtils.getGenreName(it) }
                btnWhiteList.setOnClickListener {
                    onItemCallbackListener.onWhiteList(movie)
                }
                if (movie.isFavorite) {
                    btnWhiteList.icon = ContextCompat.getDrawable(itemBinding.root.context, R.drawable.ic_heart_fill)
                } else {
                    btnWhiteList.icon = ContextCompat.getDrawable(itemBinding.root.context, R.drawable.ic_heart_outlined)
                }

                root.setOnClickListener {
                    onItemCallbackListener.onClickItem(movie)
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}