package com.app.data.mapper

import com.app.data.base.Mapper
import com.app.data.network.entites.CastResponse
import com.app.domain.entities.CastInfo

class CastMapper : Mapper<CastResponse, List<CastInfo>> {
    override fun map(input: CastResponse): List<CastInfo> {
        return input.cast?.map {
            CastInfo(
                it.profile_path.orEmpty()
            )
        }.orEmpty()
    }
}