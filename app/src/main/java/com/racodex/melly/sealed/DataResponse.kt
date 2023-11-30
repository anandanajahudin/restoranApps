package com.racodex.melly.sealed

sealed class DataResponse<T>(
    var data: T? = null,
    var error: com.racodex.melly.sealed.Error? = null,
) {
    class Success<T>(data: T) : DataResponse<T>(data = data)
    class Error<T>(error: com.racodex.melly.sealed.Error) : DataResponse<T>(error = error)
}