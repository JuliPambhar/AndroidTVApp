package com.app.androidtvapp.data.repo

import com.app.androidtvapp.data.remote.ApiInterface
import com.app.androidtvapp.data.remote.MovieDetail
import com.app.androidtvapp.data.remote.Movies
import javax.inject.Inject

class MovieRepo @Inject constructor(private val apiInterface: ApiInterface) {

    /*  suspend fun getMovies(): List<MovieItem> {
          return apiInterface.getMovies()
      }*/

    suspend fun getMovies(): Movies {
        return apiInterface.getNowPlayingMovies("57c185e7c6968fdeb7ac738b9f0465e6")
    }

    suspend fun getMoviesDetail(id: String): MovieDetail {
        return apiInterface.getMovieDetail(id, "57c185e7c6968fdeb7ac738b9f0465e6")
    }
}