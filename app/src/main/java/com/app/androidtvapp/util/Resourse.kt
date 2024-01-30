package com.app.androidtvapp.util

sealed class Resourse<T> {
    class Idle<T>:Resourse<T>()
    class Loading<T>:Resourse<T>()
    data class Success<T>(
        val data:T,
        val message:String? =null
    ):Resourse<T>()
    data class Error<T>(
        val errorMessage:String
    ):Resourse<T>()
}