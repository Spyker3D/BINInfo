package com.spyker3d.bininfo.search.data.dto

open class NetworkResponse {
    var errorMessage: String? = null
    var resultCode = -1

    companion object {
        const val SUCCESS = 200
        const val FAILURE = 500
        const val NOT_FOUND = 404
        const val NO_INTERNET = -1
        const val SOCKET_TIMEOUT = -2
    }
}