package com.app.data.repositories

import com.app.data.mapper.CastMapper
import com.app.data.mapper.MovieDetailMapper
import com.app.data.mapper.MovieMapper
import com.app.data.network.ApiInterface
import com.app.domain.entities.CastInfo
import com.app.domain.entities.MovieDetailInfo
import com.app.domain.entities.MoviesInfo
import com.app.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface,
    private val movieMapper: MovieMapper,
    private val movieDetailMapper: MovieDetailMapper,
    private val castMapper: CastMapper
) : MovieRepository {
    override suspend fun getMovies(): List<MoviesInfo> {
        return movieMapper.map(apiInterface.getNowPlayingMovies("57c185e7c6968fdeb7ac738b9f0465e6"))
    }

    override suspend fun getMoviesDetail(id: String): MovieDetailInfo {
        return movieDetailMapper.map(apiInterface.getMovieDetail(id,"57c185e7c6968fdeb7ac738b9f0465e6"))
    }

    override suspend fun getMoviesCastList(id: String): List<CastInfo> {
        return castMapper.map(apiInterface.getMovieCastList(id,"57c185e7c6968fdeb7ac738b9f0465e6"))
    }
}