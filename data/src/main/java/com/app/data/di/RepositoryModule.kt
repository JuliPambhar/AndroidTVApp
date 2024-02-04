package com.app.data.di

import com.app.data.mapper.CastMapper
import com.app.data.mapper.MovieDetailMapper
import com.app.data.mapper.MovieMapper
import com.app.data.network.ApiInterface
import com.app.data.repositories.MovieRepositoryImpl
import com.app.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        apiInterface: ApiInterface,
        movieMapper: MovieMapper,
        movieDetailMapper: MovieDetailMapper,
        castMapper: CastMapper
    ): MovieRepository {
        return MovieRepositoryImpl(
            apiInterface,
            movieMapper,
            movieDetailMapper,
            castMapper
        )
    }
}