package com.app.domain.usecase

import com.app.domain.ResponseState
import com.app.domain.entities.MovieDetailInfo
import com.app.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend fun getMovieDetail(id: String): Flow<ResponseState<MovieDetailInfo>> {
        return flow {
            try {
                emit(ResponseState.Loading())
                emit(
                    ResponseState.Success(movieRepository.getMoviesDetail(id))
                )
            } catch (e: Exception) {
                emit(ResponseState.Error(e))
            }
        }
    }
}