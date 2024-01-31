package com.app.androidtvapp.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import com.app.androidtvapp.R
import com.app.androidtvapp.databinding.ActivityMainBinding
import com.app.androidtvapp.ui.HomeFragmentDirections
import com.app.androidtvapp.ui.detail.DetailFragmentArgs
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
        viewModel.clickedMovie.observe(this) {movie->

            val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
            val action = HomeFragmentDirections.actionHomeToDetail(movie.id.toString())
            navController.navigate(action)

        }

    }
}