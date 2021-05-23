package io.indrian.whatmovies.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import io.indrian.whatmovies.R
import io.indrian.whatmovies.databinding.ActivityDetailBinding

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
        _binding?.toolbarLayout?.let {
            
            // Set Properties
            it.tvTitle.text = getString(R.string.detail)
            it.btnStart.icon = ContextCompat.getDrawable(this, R.drawable.ic_back)

            // Set Listener
            it.btnStart.setOnClickListener { finish() }
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