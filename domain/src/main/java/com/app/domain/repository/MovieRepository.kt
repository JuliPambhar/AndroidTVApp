package com.app.domain.repository

import com.app.domain.entities.CastInfo
import com.app.domain.entities.MovieDetailInfo
import com.app.domain.entities.MoviesInfo

interface MovieRepository {
    suspend fun getMovies(): List<MoviesInfo>

    suspend fun getMoviesDetail(id: String): MovieDetailInfo

    suspend fun getMoviesCastList(id: String): List<CastInfo>
}