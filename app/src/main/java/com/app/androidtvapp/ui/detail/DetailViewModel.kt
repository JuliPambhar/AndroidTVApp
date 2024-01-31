package com.app.androidtvapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.androidtvapp.data.remote.MovieDetail
import com.app.androidtvapp.data.remote.Movies
import com.app.androidtvapp.data.repo.MovieRepo
import com.app.androidtvapp.util.Resourse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DetailViewModel @Inject constructor(private val movieRepo: MovieRepo) : ViewModel() {

    private val _movieResponse =
        MutableStateFlow<Resourse<MovieDetail>>(Resourse.Idle())
    val movieResponse = _movieResponse.asStateFlow()

    fun getMovies(id:String) {
        viewModelScope.launch {
            with(_movieResponse) {
                tryEmit(Resourse.Loading())
                tryEmit(
                    Resourse.Success(
                        movieRepo.getMoviesDetail(id)
                    )
                )
            }
        }
    }
}