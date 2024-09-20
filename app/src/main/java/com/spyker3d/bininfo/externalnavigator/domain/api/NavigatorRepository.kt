package com.spyker3d.bininfo.externalnavigator.domain.api

interface NavigatorRepository {
    fun openUrl(email: String)

    fun dialNumber(number: String)
}