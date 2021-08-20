package com.hefny.hady.fakedownload.utils

/**
 * A generic class that contains data and status about loading this data.
 */
data class Resource<T>(
    var loading: Boolean = false,
    var error: String? = null,
    var data: T? = null,
    var message: String? = null,
    var id: Int? = null
) {
    companion object {
        fun <T> loading(isLoading: Boolean): Resource<T> {
            return Resource(isLoading)
        }

        fun <T> error(message: String?): Resource<T> {
            return Resource(error = message)
        }

        fun <T> data(data: T? = null, message: String? = null, id: Int? = null): Resource<T> {
            return Resource(data = data, message = message, id = id)
        }
    }
}