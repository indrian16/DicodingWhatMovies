package io.indrian.whatmovies.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import io.indrian.whatmovies.BuildConfig
import io.indrian.whatmovies.databinding.ActivitySplashBinding
import io.indrian.whatmovies.ui.main.MainActivity
import io.indrian.whatmovies.utils.AppConstants

class SplashActivity : AppCompatActivity() {

    private var _binding: ActivitySplashBinding? = null
    private val binding: ActivitySplashBinding get() = _binding!!

    private val runnable = Runnable {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        finishAffinity()
    }
    private val handler = Handler(Looper.getMainLooper())

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvVersion.text = "v${BuildConfig.VERSION_NAME}"

        handler.postDelayed(runnable, AppConstants.SPLASH_DURATION)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        handler.removeCallbacks(runnable)
        handler.removeCallbacksAndMessages(null)
    }
}