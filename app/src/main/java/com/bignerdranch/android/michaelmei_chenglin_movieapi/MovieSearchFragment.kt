package com.bignerdranch.android.michaelmei_chenglin_movieapi

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import api.MovieApi
import com.bignerdranch.android.michaelmei_chenglin_movieapi.databinding.FragmentMovieSearchBinding
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.create

private const val TAG = "MovieSearchFragment"

class MovieSearchFragment : Fragment() {
    private var _binding: FragmentMovieSearchBinding? = null
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
            FragmentMovieSearchBinding.inflate(inflater, container, false)
        val constraintLayout = ConstraintLayout(requireContext())
        binding.root.addView(constraintLayout)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewLifecycleOwner.lifecycleScope.launch {
            try {

                val response = MovieRepository().fetchMovie()
                Log.d(TAG, "Response received: $response")
            } catch (ex: Exception) {
                Log.e(TAG, "Failed to fetch gallery items", ex)
            }
        }
    }
}
