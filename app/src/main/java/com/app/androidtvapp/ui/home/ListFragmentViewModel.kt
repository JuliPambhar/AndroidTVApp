package com.app.androidtvapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.domain.ResponseState
import com.app.domain.entities.MoviesInfo
import com.app.domain.usecase.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListFragmentViewModel @Inject constructor(private val getMovieUseCase: GetMovieUseCase) :
    ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _movieResponse = MutableLiveData<List<MoviesInfo>>()
    val movieResponse: LiveData<List<MoviesInfo>> = _movieResponse

    private val _selectedMovie = MutableLiveData<MoviesInfo>()
    val selectedMovie: LiveData<MoviesInfo> = _selectedMovie

    private val _clickedMovie = MutableLiveData<MoviesInfo>()
    val clickedMovie: LiveData<MoviesInfo> = _clickedMovie

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            getMovieUseCase.getMovies().collect { response ->
                when (response) {
                    is ResponseState.Error -> {
                        println(response.throwable.message)
                    }

                    is ResponseState.Loading -> {
                        _loading.value = true
                    }

                    is ResponseState.Success -> {
                        _loading.value = false
                        _movieResponse.value = response.data!!
                    }
                }
            }
        }
    }

    fun onItemSelected(item: MoviesInfo) {
        _selectedMovie.value = item
    }

    fun onItemClicked(item: MoviesInfo) {
        _clickedMovie.value = item
    }
}