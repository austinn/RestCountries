package com.example.restcountries.data.remote

data class ApiResult<out T>(
    val status: Status,
    val data: T?,
    val message: String?
) {
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): ApiResult<T> = ApiResult(Status.SUCCESS, data, null)
        fun <T> error(message: String, data: T? = null) = ApiResult(Status.ERROR, data, message)
        fun <T> loading(data: T? = null) = ApiResult(Status.LOADING, data, null)
    }
}