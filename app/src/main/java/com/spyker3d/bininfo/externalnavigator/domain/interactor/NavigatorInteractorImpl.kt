package com.spyker3d.bininfo.externalnavigator.domain.interactor

import com.spyker3d.bininfo.externalnavigator.domain.api.NavigatorInteractor
import com.spyker3d.bininfo.externalnavigator.domain.api.NavigatorRepository

class NavigatorInteractorImpl(private val navigatorRepository: NavigatorRepository) :
    NavigatorInteractor {
    override fun openUrl(url: String) {
        navigatorRepository.openUrl(url)
    }

    override fun dialNumber(number: String) {
        navigatorRepository.dialNumber(number)
    }

}