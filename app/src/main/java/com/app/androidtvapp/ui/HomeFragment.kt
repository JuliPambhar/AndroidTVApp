package com.app.androidtvapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.app.androidtvapp.data.remote.MovieItem
import com.app.androidtvapp.databinding.FragmentHomeBinding
import com.app.androidtvapp.ui.home.ListFragmentViewModel

class HomeFragment : Fragment() {

    private val viewModel: ListFragmentViewModel by activityViewModels()

    private lateinit var binding: FragmentHomeBinding

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

    fun init() {

        viewModel.selectedMovie.observe(viewLifecycleOwner) {
            updateView(it)
        }
    }

    private fun updateView(movieItem: MovieItem) {
        println("selected ${movieItem}")
        binding.thumbImage.load(movieItem.image_url)
        binding.title.text = movieItem.name
        binding.subTitle.text = movieItem.year.toString()
        binding.description.text = movieItem.desc
    }
}


