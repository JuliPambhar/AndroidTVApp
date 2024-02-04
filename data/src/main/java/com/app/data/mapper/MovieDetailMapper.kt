package com.app.data.mapper

import com.app.data.base.Mapper
import com.app.data.network.entites.MovieDetail
import com.app.domain.entities.MovieDetailInfo

class MovieDetailMapper : Mapper<MovieDetail, MovieDetailInfo> {
    override fun map(input: MovieDetail): MovieDetailInfo {
        return MovieDetailInfo(
            input.title.orEmpty(),
            input.adult ?: false,
            input.genres?.map { it.name }.orEmpty(),
            input.runtime.toString(),
            input.overview.orEmpty(),
            input.backdrop_path.orEmpty()
        )
    }
}