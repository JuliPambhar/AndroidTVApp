package com.app.data.mapper

import com.app.data.base.Mapper
import com.app.data.network.entites.Movies
import com.app.domain.entities.MoviesInfo

class MovieMapper : Mapper<Movies, List<MoviesInfo>> {
    override fun map(input: Movies): List<MoviesInfo> {
        return input.results?.map { movieItem ->
            MoviesInfo(
                movieItem.id.toString(),
                movieItem.poster_path.orEmpty(),
                movieItem.title.orEmpty(),
                movieItem.release_date.orEmpty(),
                movieItem.overview.orEmpty(),
                movieItem.backdrop_path.orEmpty()
            )
        }.orEmpty()
    }
}