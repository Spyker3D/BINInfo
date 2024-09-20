package com.spyker3d.bininfo.externalnavigator.domain.api

interface NavigatorInteractor {
    fun openUrl(email: String)

    fun dialNumber(number: String)
}