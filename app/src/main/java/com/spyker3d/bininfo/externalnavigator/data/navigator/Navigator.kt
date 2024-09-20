package com.spyker3d.bininfo.externalnavigator.data.navigator

import android.content.Context
import android.content.Intent
import android.net.Uri

class Navigator(private val context: Context) {

    fun openUrl(url: String) {
        Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse(url)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(this)
        }
    }

    fun dialNumber(number: String) {
        Intent().apply {
            action = Intent.ACTION_DIAL
            data = Uri.parse("tel:$number")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(this)
        }
    }
}