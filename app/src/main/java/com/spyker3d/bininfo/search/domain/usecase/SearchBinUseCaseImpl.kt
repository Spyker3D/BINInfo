package com.spyker3d.bininfo.search.domain.usecase

import com.spyker3d.bininfo.search.domain.api.BinSearchRepository
import com.spyker3d.bininfo.search.domain.api.SearchBinUseCase
import com.spyker3d.bininfo.search.domain.entities.CardInfo
import com.spyker3d.bininfo.utils.Resource

class SearchBinUseCaseImpl(private val binSearchRepository: BinSearchRepository) : SearchBinUseCase {
    override suspend fun execute(binNumber: String): Resource<CardInfo> {
        return binSearchRepository.searchBin(binNumber)
    }
}