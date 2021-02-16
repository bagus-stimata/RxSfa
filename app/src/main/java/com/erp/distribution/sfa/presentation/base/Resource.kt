package com.erp.distribution.sfa.presentation.base

sealed class Resource<out T> {
    class Loading<out T>(val b: Boolean) : Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure<out T>(val exception: Exception) : Resource<T>()
}