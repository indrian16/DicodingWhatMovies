package io.indrian.whatmovies.ui.detail

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.Chip
import io.indrian.whatmovies.R
import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.data.models.TVShow
import io.indrian.whatmovies.databinding.ActivityDetailBinding
import io.indrian.whatmovies.di.GlideApp
import io.indrian.whatmovies.utils.AppUtils
import io.indrian.whatmovies.utils.toGone
import io.indrian.whatmovies.utils.toast

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding: ActivityDetailBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()

        // Setup ViewModel
        val viewModel: DetailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]

        // Handle Type Detail
        val isMovie = intent?.getBooleanExtra(IS_MOVIE_EXTRA, true) ?: true
        val id = intent?.getLongExtra(ID_EXTRA, 0L) ?: 0L
        if (isMovie) {
            //val movie = viewModel.getDetailMovies(id)
            //displayMovie(movie)
        } else {
            //val tvShow = viewModel.getDetailTVShow(id)
            //displayTVShow(tvShow)
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
        }
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
                    setOnClickListener { toast(getString(R.string.coming_soon)) }
                }
            }.forEach {
                genreGroup.addView(it)
            }

            binding.toolbarLayout.btnEnd.setOnClickListener {
                shareIt(movie.title, movie.overview)
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
                    setOnClickListener { toast(getString(R.string.coming_soon)) }
                }
            }.forEach {
                genreGroup.addView(it)
            }

            binding.toolbarLayout.btnEnd.setOnClickListener {
                shareIt(tvShow.name, tvShow.overview)
            }
        }
    }

    /**
     * Share Information:
     * Title
     *
     * Overview
     * Text Body
     * */
    private fun shareIt(title: String, overview: String) {
        val text = "$title\n\n${getString(R.string.overview)}\n$overview"
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, "${getString(R.string.you_share)} $title ?")
        startActivity(shareIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val ID_EXTRA = "id_extra"
        private const val IS_MOVIE_EXTRA = "is_movie_extra"

        fun push(activity: Activity, id: Long, isMovie: Boolean) {
            Intent(activity, DetailActivity::class.java).apply {
                putExtra(ID_EXTRA, id)
                putExtra(IS_MOVIE_EXTRA, isMovie)
            }.run {
                activity.startActivity(this)
            }
        }
    }
}