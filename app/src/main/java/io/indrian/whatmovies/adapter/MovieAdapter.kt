package io.indrian.whatmovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.databinding.ItemLayoutBinding
import io.indrian.whatmovies.di.GlideApp
import io.indrian.whatmovies.utils.AppUtils

class MovieAdapter(private val onItemCallbackListener: OnItemCallbackListener) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var movies: List<Movie> = arrayListOf()

    fun add(movies: List<Movie>) {
        this.movies = movies
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
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    interface OnItemCallbackListener {
        fun onClickItem(movie: Movie)
    }

    inner class ViewHolder(private val itemBinding: ItemLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(movie: Movie) {
            with(itemBinding) {
                GlideApp.with(this.root)
                    .load(AppUtils.getImagePath(movie.posterPath))
                    .into(imagePoster)

                tvTitle.text = movie.title
                ratingBar.rating = AppUtils.getFiveStar(movie.voteAverage)
                tvRating.text = movie.voteAverage.toString()
                tvGenre.text = movie.genreIds.joinToString { AppUtils.getMovieGenre(it) }

                root.setOnClickListener {
                    onItemCallbackListener.onClickItem(movie)
                }
            }
        }
    }
}