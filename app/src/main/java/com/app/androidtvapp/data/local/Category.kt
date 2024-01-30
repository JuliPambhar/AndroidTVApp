package com.app.androidtvapp.data.local

import com.app.androidtvapp.data.remote.MovieItem

data class Category(
    val id: Long,
    val genre: String,
    val movies: List<MovieItem>
)
