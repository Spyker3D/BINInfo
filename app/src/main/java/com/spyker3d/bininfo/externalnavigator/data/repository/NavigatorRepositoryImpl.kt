package com.spyker3d.bininfo.externalnavigator.data.repository

import com.spyker3d.bininfo.externalnavigator.data.navigator.Navigator
import com.spyker3d.bininfo.externalnavigator.domain.api.NavigatorRepository

class NavigatorRepositoryImpl(private val externalNavigator: Navigator) : NavigatorRepository {
    override fun openUrl(url: String) {
        externalNavigator.openUrl(url)
    }

    override fun dialNumber(number: String) {
        externalNavigator.dialNumber(number)
    }

}