package com.bignerdranch.android.michaelmei_chenglin_movieapi

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import api.MovieApi
import api.MovieItem
import com.bignerdranch.android.michaelmei_chenglin_movieapi.databinding.FragmentMovieSearchBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.create

private const val TAG = "MovieSearchFragment"

class MovieSearchFragment : Fragment() {
    private lateinit var movieTitle: String

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

        val editText: EditText =  binding.textView

        val button: Button = binding.button

        var response: MovieItem? = null

        button.setOnClickListener{
            movieTitle = editText.text.toString()
            viewLifecycleOwner.lifecycleScope.launch {
                try {
                    response = MovieRepository().fetchMovie(movieTitle)
                    Log.d(TAG, "Response received: $response")

                    val bundle = Bundle()
                    bundle.putString("title", response?.title)
                    bundle.putString("posterUrl", response?.url)

                    val action = MovieSearchFragmentDirections.actionMovieSearchFragmentToMovieDetailFragment(bundle)
                    findNavController().navigate(action)
                } catch (ex: Exception) {
                    Toast.makeText(view.context, "Movie not found", Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "Failed to fetch movie item", ex)
                }
            }
        }
    }
}
