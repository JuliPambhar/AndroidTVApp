package com.app.androidtvapp.util.flow

import kotlinx.coroutines.flow.MutableSharedFlow

fun <T> mutableEventFlow():MutableSharedFlow<T>{
    return MutableSharedFlow(
        replay = 0,
        extraBufferCapacity = 1
    )
}