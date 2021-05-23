package io.indrian.whatmovies.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import io.indrian.whatmovies.adapter.ItemAdapter
import io.indrian.whatmovies.databinding.FragmentTVShowBinding
import io.indrian.whatmovies.ui.detail.DetailActivity

class TVShowFragment : Fragment(), ItemAdapter.OnItemCallbackListener {

    private var _binding: FragmentTVShowBinding? = null
    private val binding: FragmentTVShowBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTVShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ItemAdapter(this)
        binding.rvTvShow.adapter = adapter
    }

    override fun onClickItem() {
        DetailActivity.push(activity as AppCompatActivity)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = TVShowFragment()
    }
}