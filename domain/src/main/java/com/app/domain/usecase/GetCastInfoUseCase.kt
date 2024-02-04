package com.app.domain.usecase

import com.app.domain.ResponseState
import com.app.domain.entities.CastInfo
import com.app.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCastInfoUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend fun getCastInfo(id: String): Flow<ResponseState<List<CastInfo>>> {
        return flow {
            try {
                emit(ResponseState.Loading())
                emit(ResponseState.Success(movieRepository.getMoviesCastList(id)))
            } catch (e: Exception) {
                emit(ResponseState.Error(e))
            }
        }
    }
}