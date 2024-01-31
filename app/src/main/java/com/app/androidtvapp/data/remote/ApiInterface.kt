package com.app.androidtvapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("movie/now_playing?language=en-US&page=1")
    suspend fun getNowPlayingMovies(@Query("api_key") apiKey: String): Movies

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id")id:String,@Query("api_key") apiKey: String): MovieDetail

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCastList(@Path("movie_id")id:String,@Query("api_key") apiKey: String): CastResponse

//    @GET("top250_min.json")
//    suspend fun getMovies(): List<MovieItem>
}