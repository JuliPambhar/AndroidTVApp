package com.app.domain.entities

data class MovieDetailInfo(
    val title: String = "",
    val rating: Boolean = false,
    val genreName: List<String> = listOf(),
    val runtime: String = "",
    val description: String = "",
    val imgBanner: String = "",
)
