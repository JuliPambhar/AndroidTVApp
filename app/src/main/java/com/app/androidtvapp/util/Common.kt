package com.app.androidtvapp.util

import android.content.Context

class Common {
    companion object{
        fun getWidthPercent(context: Context, percent: Int): Int {
            val width = context.resources.displayMetrics.widthPixels ?: 0
            return (width * percent) / 100
        }

        fun getHeightPercent(context: Context, percent: Int): Int {
            val width = context.resources.displayMetrics.heightPixels ?: 0
            return (width * percent) / 100
        }
    }
}