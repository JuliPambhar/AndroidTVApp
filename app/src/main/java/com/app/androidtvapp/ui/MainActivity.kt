package com.app.androidtvapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import coil.load
import com.app.androidtvapp.data.remote.MovieItem
import com.app.androidtvapp.databinding.ActivityMainBinding
import com.app.androidtvapp.ui.home.ListFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private val viewModel: ListFragmentViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel.selectedMovie.observe(this) {
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


