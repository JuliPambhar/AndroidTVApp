package com.app.domain.usecase

import com.app.domain.ResponseState
import com.app.domain.entities.MoviesInfo
import com.app.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend fun getMovies(): Flow<ResponseState<List<MoviesInfo>>> {
        return flow {
            try {
                emit(ResponseState.Loading())
                emit(ResponseState.Success(movieRepository.getMovies()))
            } catch (e: Exception) {
                emit(ResponseState.Error(e))
            }
        }
    }
}