package com.spyker3d.bininfo.search.domain.api

import com.spyker3d.bininfo.search.domain.entities.CardInfo
import com.spyker3d.bininfo.utils.Resource

interface SearchBinUseCase {
    suspend fun execute(binNumber: String): Resource<CardInfo>
}