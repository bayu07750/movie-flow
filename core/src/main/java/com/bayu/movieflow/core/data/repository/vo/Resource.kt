package com.bayu.movieflow.core.data.repository.vo

sealed class Resource<T> {

    class Loading<T> : Resource<T>()

    class Success<T>(val data: T) : Resource<T>()

    class Error<T>(val message: String) : Resource<T>()

    class Empty<T>(val message: String? = null) : Resource<T>()

}