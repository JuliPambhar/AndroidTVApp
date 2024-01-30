package com.app.androidtvapp.data.repo

import com.app.androidtvapp.data.remote.ApiInterface
import com.app.androidtvapp.data.remote.MovieItem
import javax.inject.Inject

class MovieRepo @Inject constructor(private val apiInterface: ApiInterface) {

    suspend fun getMovies(): List<MovieItem> {
        return apiInterface.getMovies()
    }
}