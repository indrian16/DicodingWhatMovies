package io.indrian.whatmovies.ui.detail

import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import io.indrian.whatmovies.R
import io.indrian.whatmovies.databinding.ActivityDetailBinding
import io.indrian.whatmovies.utils.toGone

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding: ActivityDetailBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
    }

    private fun setupToolbar() {
        // Set AppBar
        binding.appBar.outlineProvider = null

        // Set Toolbar
        with(binding.toolbarLayout) {
            toolbar.setBackgroundColor(ContextCompat.getColor(this@DetailActivity, android.R.color.transparent))
            tvTitle.toGone()
            btnStart.setIconResource(R.drawable.ic_back)
            btnStart.setIconTintResource(R.color.colorPrimary)
            btnStart.strokeColor = ColorStateList.valueOf(ContextCompat.getColor(root.context, R.color.colorPrimary))
            btnEnd.setIconTintResource(R.color.colorPrimary)
            btnEnd.strokeColor = ColorStateList.valueOf(ContextCompat.getColor(root.context, R.color.colorPrimary))

            btnStart.setOnClickListener { finish() }
            btnEnd.setOnClickListener {  }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun push(activity: AppCompatActivity) {
            val intent = Intent(activity, DetailActivity::class.java)
            activity.startActivity(intent)
        }
    }
}