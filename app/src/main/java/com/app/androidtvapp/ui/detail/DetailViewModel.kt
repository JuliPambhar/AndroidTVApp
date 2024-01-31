package com.app.androidtvapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.androidtvapp.data.remote.CastResponse
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

    private val _castResponse =
        MutableStateFlow<Resourse<CastResponse>>(Resourse.Idle())
    val castResponse= _castResponse.asStateFlow()

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
    fun getMoviesCast(id:String) {
        viewModelScope.launch {
            with(_castResponse) {
                tryEmit(Resourse.Loading())
                tryEmit(
                    Resourse.Success(
                        movieRepo.getMoviesCastList(id)
                    )
                )
            }
        }
    }

}