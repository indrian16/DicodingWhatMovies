package io.indrian.whatmovies.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import io.indrian.whatmovies.R
import io.indrian.whatmovies.databinding.ActivityMainBinding
import io.indrian.whatmovies.utils.toast

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setBottomNav()
    }

    private fun setupToolbar() {
        with(binding.toolbarLayout) {
            btnStart.setOnClickListener { toast(getString(R.string.coming_soon)) }
            btnEnd.setOnClickListener { toast(getString(R.string.coming_soon)) }
        }
    }

    private fun setBottomNav() {
        val controller = findNavController(R.id.nav_host_fragment)
        binding.bottomNav.setupWithNavController(controller)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}