package io.indrian.whatmovies.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
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
        setupTabPager()
    }

    private fun setupToolbar() {
        with(binding.toolbarLayout) {
            btnStart.setOnClickListener { toast(getString(R.string.coming_soon)) }
            btnEnd.setOnClickListener { toast(getString(R.string.coming_soon)) }
        }
    }

    private fun setupTabPager() {
        val titles = intArrayOf(R.string.movies, R.string.tv_show)
        val sectionsPagerAdapter = SectionsPagerAdapter(this)

        with(binding) {

            // Set Pager Adapter
            viewPager.adapter = sectionsPagerAdapter

            // Connect Tab and ViewPager
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = resources.getString(titles[position])
            }.attach()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}