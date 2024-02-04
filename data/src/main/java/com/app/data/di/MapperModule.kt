package com.app.data.di

import com.app.data.mapper.CastMapper
import com.app.data.mapper.MovieDetailMapper
import com.app.data.mapper.MovieMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    fun provideMovieMapper(): MovieMapper {
        return MovieMapper()
    }

    @Provides
    fun provideMovieDetailMapper(): MovieDetailMapper {
        return MovieDetailMapper()
    }

    @Provides
    fun provideCastMapper(): CastMapper {
        return CastMapper()
    }
}