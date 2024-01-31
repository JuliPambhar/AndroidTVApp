package com.app.androidtvapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.androidtvapp.data.remote.Movies
import com.app.androidtvapp.data.remote.Result
import com.app.androidtvapp.data.repo.MovieRepo
import com.app.androidtvapp.util.Resourse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListFragmentViewModel @Inject constructor(private val movieRepo: MovieRepo) : ViewModel() {

    private val _movieResponse =
        MutableStateFlow<Resourse<Movies>>(Resourse.Idle())
    val movieResponse = _movieResponse.asStateFlow()

    private val _selectedMovie = MutableLiveData<Result>()
    val selectedMovie: LiveData<Result> = _selectedMovie

    private val _clickedMovie = MutableLiveData<Result>()
    val clickedMovie: LiveData<Result> = _clickedMovie

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            with(_movieResponse) {
                tryEmit(Resourse.Loading())
                tryEmit(
                    Resourse.Success(
                        movieRepo.getMovies()
                    )
                )
            }
        }
    }

    /** TO convert movie list to categorized feed*/
/*    private fun List<MovieItem>.categorized(): List<Category> {
        val genreSet = mutableSetOf<String>()
        *//*  this.map { movieItem ->
              movieItem.genre.map {
                  genreSet.add(it)
              }.distinct()
          }*//*
        for (movie in this) {
            for (genre in movie.genre) {
                genreSet.add(genre)
            }
        }

        val feedItems = mutableListOf<Category>()

        for ((index, genre) in genreSet.withIndex()) {
            val genreMovies = this.filter {
                it.genre.contains(genre)
            }

            feedItems.add(Category(index.toLong(), genre, genreMovies))
        }
        return feedItems
    }*/

    fun onItemSelected(item: Result) {
        _selectedMovie.value = item
    }

    fun onItemClicked(item: Result) {
        _clickedMovie.value = item
    }
}