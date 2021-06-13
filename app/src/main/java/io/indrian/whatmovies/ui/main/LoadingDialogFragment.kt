package io.indrian.whatmovies.ui.main

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import io.indrian.whatmovies.R
import io.indrian.whatmovies.databinding.FragmentLoadingDialogBinding

class LoadingDialogFragment : DialogFragment() {

    private var _binding: FragmentLoadingDialogBinding? = null
    private val binding: FragmentLoadingDialogBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        isCancelable = false
        _binding = FragmentLoadingDialogBinding.inflate(
            LayoutInflater.from(context),
            container,
            false
        )
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val window = dialog?.window
        if (window != null) {

            val params = window.attributes
            params.width = resources.getDimensionPixelSize(R.dimen.loading_size)
            params.height = resources.getDimensionPixelSize(R.dimen.loading_size)

            window.attributes = params
            window.setGravity(Gravity.CENTER)
            window.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        const val TAG = "LoadingDialogFragment"

        fun newInstance() = LoadingDialogFragment()
    }
}