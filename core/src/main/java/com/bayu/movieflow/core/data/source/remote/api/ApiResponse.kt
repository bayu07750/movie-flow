package com.bayu.movieflow.core.data.source.remote.api

sealed class ApiResponse <T> {

    data class Success <T>(val data: T) : ApiResponse<T>()

    data class Error <T>(val message: String) : ApiResponse<T>()

    class Empty<T> : ApiResponse<T>()

}