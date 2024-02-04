package com.app.androidtvapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.app.androidtvapp.R
import com.app.androidtvapp.databinding.FragmentHomeBinding
import com.app.domain.entities.MoviesInfo

class HomeFragment : Fragment() {

    private val viewModel: ListFragmentViewModel by activityViewModels()

    private lateinit var binding: FragmentHomeBinding

    private lateinit var listFragment: ListFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        listFragment = ListFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.add(R.id.list_fragment, listFragment)
        transaction.commit()

        viewModel.loading.observe(viewLifecycleOwner) {
            binding.loading.isVisible = it
        }

        viewModel.movieResponse.observe(viewLifecycleOwner) { resource ->
            listFragment.displayData(resource, "Now Playing")
        }
        viewModel.movieResponse.observe(viewLifecycleOwner) { resource ->
            listFragment.displayData(resource, "Top Rated")
        }
        viewModel.selectedMovie.observe(viewLifecycleOwner) {
            updateView(it)
        }
    }

    private fun updateView(movieItem: MoviesInfo) {
        binding.thumbImage.load("https://www.themoviedb.org/t/p/w780" + movieItem.backdropPath)
        binding.title.text = movieItem.title
        binding.subTitle.text = movieItem.releaseDate
        binding.description.text = movieItem.overview
    }
}


