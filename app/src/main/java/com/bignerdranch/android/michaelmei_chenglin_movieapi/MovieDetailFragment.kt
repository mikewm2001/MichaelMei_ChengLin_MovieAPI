package com.bignerdranch.android.michaelmei_chenglin_movieapi

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import coil.load
import com.bignerdranch.android.michaelmei_chenglin_movieapi.databinding.FragmentMovieDetailBinding
import com.google.android.material.snackbar.Snackbar

private const val TAG = "MovieDetailFragment"
class MovieDetailFragment : Fragment() {
    private var _binding: FragmentMovieDetailBinding? = null

    private var snackbar: Snackbar? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentMovieDetailBinding.inflate(inflater, container, false)
        val constraintLayout = ConstraintLayout(requireContext())
        binding.root.addView(constraintLayout)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments?.getBundle("bundleKey")

        val title = bundle?.getString("title")
        val poserUrl = bundle?.getString("posterUrl")

        Log.d(TAG, "onViewCreated: title $title")
        Log.d(TAG, "onViewCreated: poserUrl: $poserUrl")
        binding.detailTitle.text = title
        if (poserUrl != null) {
            binding.poster.load(poserUrl)
        }

        if (poserUrl == "N/A") {
            snackbar = Snackbar.make(view, "No Image Available", Snackbar.LENGTH_INDEFINITE)
            snackbar?.show()
        }

        snackbar?.setAction("Dismiss") {
            snackbar?.dismiss()
        }



    }
}