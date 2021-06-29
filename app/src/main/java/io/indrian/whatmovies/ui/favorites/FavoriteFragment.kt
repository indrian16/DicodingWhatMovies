package io.indrian.whatmovies.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import io.indrian.whatmovies.R
import io.indrian.whatmovies.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding: FragmentFavoriteBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(
            LayoutInflater.from(context),
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabPager()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoriteFragment()
    }
}