package com.app.androidtvapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.domain.ResponseState
import com.app.domain.entities.CastInfo
import com.app.domain.entities.MovieDetailInfo
import com.app.domain.usecase.GetCastInfoUseCase
import com.app.domain.usecase.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getCastInfoUseCase: GetCastInfoUseCase
) : ViewModel() {

    private val _movieResponse = MutableLiveData<MovieDetailInfo>()
    val movieResponse: LiveData<MovieDetailInfo> = _movieResponse

    private val _castResponse = MutableLiveData<List<CastInfo>>()
    val castResponse: LiveData<List<CastInfo>> = _castResponse

    fun getMovies(id: String) {
        viewModelScope.launch {
            getMovieDetailUseCase.getMovieDetail(id).collect { response ->
                when (response) {
                    is ResponseState.Error -> {

                    }

                    is ResponseState.Loading -> {}
                    is ResponseState.Success -> {
                        _movieResponse.value = response.data!!
                    }
                }

            }
        }
    }

    fun getMoviesCast(id: String) {
        viewModelScope.launch {
            getCastInfoUseCase.getCastInfo(id).collect { response ->
                when (response) {
                    is ResponseState.Error -> {}
                    is ResponseState.Loading -> {

                    }

                    is ResponseState.Success -> {
                        _castResponse.value = response.data!!
                    }
                }
            }
        }
    }

}