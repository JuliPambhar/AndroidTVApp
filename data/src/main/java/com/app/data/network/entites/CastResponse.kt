package com.app.data.network.entites

data class CastResponse(
    val cast: List<Cast>?,
    val crew: List<Crew>?,
    val id: Int?
)