package com.app.androidtvapp.data.remote

data class CastResponse(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)