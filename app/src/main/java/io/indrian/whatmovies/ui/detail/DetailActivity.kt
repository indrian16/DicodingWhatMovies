package io.indrian.whatmovies.ui.detail

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import io.indrian.whatmovies.R
import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.data.models.TVShow
import io.indrian.whatmovies.databinding.ActivityDetailBinding
import io.indrian.whatmovies.di.GlideApp
import io.indrian.whatmovies.utils.AppUtils
import io.indrian.whatmovies.utils.toGone

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding: ActivityDetailBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()

        val movie = intent.getParcelableExtra<Movie>(MOVIE_EXTRA)
        val tvShow = intent.getParcelableExtra<TVShow>(TV_SHOW_EXTRA)

        if (movie != null) {
            displayMovie(movie)
        }

        if (tvShow != null) {
            displayTVShow(tvShow)
        }
    }

    private fun setupToolbar() {
        // Set AppBar
        binding.appBar.outlineProvider = null

        // Set Toolbar
        with(binding.toolbarLayout) {
            toolbar.setBackgroundColor(ContextCompat.getColor(this@DetailActivity, android.R.color.transparent))
            tvTitle.toGone()
            btnStart.setIconResource(R.drawable.ic_back)
            btnStart.setIconTintResource(R.color.white)
            btnStart.strokeColor = ColorStateList.valueOf(ContextCompat.getColor(root.context, R.color.white))
            btnEnd.setIconResource(R.drawable.ic_share)
            btnEnd.setIconTintResource(R.color.white)
            btnEnd.strokeColor = ColorStateList.valueOf(ContextCompat.getColor(root.context, R.color.white))

            btnStart.setOnClickListener { finish() }
            btnEnd.setOnClickListener {  }
        }
    }

    private fun shareMovie() {

    }

    @SuppressLint("SetTextI18n")
    private fun displayMovie(movie: Movie) {
        with(binding) {
            GlideApp.with(this@DetailActivity)
                .load(AppUtils.getImagePath(movie.backdropPath))
                .into(imageBackdrop)
            GlideApp.with(this@DetailActivity)
                .load(AppUtils.getImagePath(movie.posterPath))
                .into(imagePoster)

            tvTitleValue.text = movie.title
            tvInformation.text = "${getString(R.string.year)}: ${AppUtils.getYear(movie.releaseDate)} | ${movie.voteAverage} "
            tvOverviewValue.text = movie.overview

            movie.genreIds.map {
                Chip(this@DetailActivity).apply {
                    text = AppUtils.getGenreName(it)
                }
            }.forEach {
                genreGroup.addView(it)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun displayTVShow(tvShow: TVShow) {
        with(binding) {
            GlideApp.with(this@DetailActivity)
                .load(AppUtils.getImagePath(tvShow.backdropPath))
                .into(imageBackdrop)
            GlideApp.with(this@DetailActivity)
                .load(AppUtils.getImagePath(tvShow.posterPath))
                .into(imagePoster)

            tvTitleValue.text = tvShow.name
            tvInformation.text = "${getString(R.string.year)}: ${AppUtils.getYear(tvShow.firstAirDate)} | ${tvShow.voteAverage} "
            tvOverviewValue.text = tvShow.overview

            tvShow.genreIds.map {
                Chip(this@DetailActivity).apply {
                    text = AppUtils.getGenreName(it)
                }
            }.forEach {
                genreGroup.addView(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val MOVIE_EXTRA = "movie_extra"
        private const val TV_SHOW_EXTRA = "tv_show_extra"

        fun pushMovie(activity: Activity, movie: Movie) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(MOVIE_EXTRA, movie)
            activity.startActivity(intent)
        }

        fun pushTVShow(activity: Activity, tvShow: TVShow) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(TV_SHOW_EXTRA, tvShow)
            activity.startActivity(intent)
        }
    }
}